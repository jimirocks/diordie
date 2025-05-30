package rocks.jimi.diordie.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import rocks.jimi.diordie.exceptions.ResourceNotFoundException
import rocks.jimi.diordie.models.Installation
import rocks.jimi.diordie.models.Installations
import java.time.Instant
import java.util.UUID

class InstallationRepository {
    
    fun findAll(): List<Installation> = transaction {
        Installations.selectAll().map { it.toInstallation() }
    }
    
    fun findById(id: UUID): Installation = transaction {
        Installations.select { Installations.id eq id }
            .map { it.toInstallation() }
            .firstOrNull() ?: throw ResourceNotFoundException("Installation", "id", id)
    }
    
    fun findByCustomerId(customerId: UUID): List<Installation> = transaction {
        Installations.select { Installations.customerId eq customerId }
            .map { it.toInstallation() }
    }
    
    fun create(installation: Installation): Installation = transaction {
        val id = UUID.randomUUID()
        Installations.insert {
            it[Installations.id] = id
            it[Installations.customerId] = installation.customerId
            it[address] = installation.address
            it[gpsLatitude] = installation.gpsLatitude
            it[gpsLongitude] = installation.gpsLongitude
            it[status] = installation.status
            it[createdAt] = installation.createdAt
            it[updatedAt] = installation.updatedAt
        }
        
        findById(id)
    }
    
    fun update(id: UUID, installation: Installation): Installation = transaction {
        val updated = Installations.update({ Installations.id eq id }) {
            it[customerId] = installation.customerId
            it[address] = installation.address
            it[gpsLatitude] = installation.gpsLatitude
            it[gpsLongitude] = installation.gpsLongitude
            it[status] = installation.status
            it[updatedAt] = Instant.now()
        }
        
        if (updated == 0) {
            throw ResourceNotFoundException("Installation", "id", id)
        }
        
        findById(id)
    }
    
    fun delete(id: UUID): Boolean = transaction {
        val deleted = Installations.deleteWhere { Installations.id eq id }
        deleted > 0
    }
    
    private fun ResultRow.toInstallation(): Installation = Installation(
        id = this[Installations.id].value,
        customerId = this[Installations.customerId].value,
        address = this[Installations.address],
        gpsLatitude = this[Installations.gpsLatitude],
        gpsLongitude = this[Installations.gpsLongitude],
        status = this[Installations.status],
        createdAt = this[Installations.createdAt],
        updatedAt = this[Installations.updatedAt]
    )
}