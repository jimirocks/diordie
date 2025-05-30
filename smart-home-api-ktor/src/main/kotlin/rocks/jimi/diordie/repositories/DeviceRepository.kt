package rocks.jimi.diordie.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import rocks.jimi.diordie.exceptions.ResourceNotFoundException
import rocks.jimi.diordie.models.Device
import rocks.jimi.diordie.models.DeviceType
import rocks.jimi.diordie.models.Devices
import java.time.Instant
import java.util.UUID

class DeviceRepository {
    
    fun findAll(): List<Device> = transaction {
        Devices.selectAll().map { it.toDevice() }
    }
    
    fun findById(id: UUID): Device = transaction {
        Devices.select { Devices.id eq id }
            .map { it.toDevice() }
            .firstOrNull() ?: throw ResourceNotFoundException("Device", "id", id)
    }
    
    fun findByInstallationId(installationId: UUID): List<Device> = transaction {
        Devices.select { Devices.installationId eq installationId }
            .map { it.toDevice() }
    }
    
    fun create(device: Device): Device = transaction {
        val id = UUID.randomUUID()
        Devices.insert {
            it[Devices.id] = id
            it[Devices.installationId] = device.installationId
            it[type] = device.type
            it[serialNumber] = device.serialNumber
            it[hwVersion] = device.hwVersion
            it[swVersion] = device.swVersion
            it[ipAddress] = device.ipAddress
            it[createdAt] = device.createdAt
            it[updatedAt] = device.updatedAt
        }
        
        findById(id)
    }
    
    fun update(id: UUID, device: Device): Device = transaction {
        val updated = Devices.update({ Devices.id eq id }) {
            it[installationId] = device.installationId
            it[type] = device.type
            it[serialNumber] = device.serialNumber
            it[hwVersion] = device.hwVersion
            it[swVersion] = device.swVersion
            it[ipAddress] = device.ipAddress
            it[updatedAt] = Instant.now()
        }
        
        if (updated == 0) {
            throw ResourceNotFoundException("Device", "id", id)
        }
        
        findById(id)
    }
    
    fun delete(id: UUID): Boolean = transaction {
        val deleted = Devices.deleteWhere { Devices.id eq id }
        deleted > 0
    }
    
    private fun ResultRow.toDevice(): Device = Device(
        id = this[Devices.id].value,
        installationId = this[Devices.installationId].value,
        type = this[Devices.type],
        serialNumber = this[Devices.serialNumber],
        hwVersion = this[Devices.hwVersion],
        swVersion = this[Devices.swVersion],
        ipAddress = this[Devices.ipAddress],
        createdAt = this[Devices.createdAt],
        updatedAt = this[Devices.updatedAt]
    )
}