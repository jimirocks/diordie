package rocks.jimi.diordie.service

import rocks.jimi.diordie.exception.ResourceAlreadyExistsException
import rocks.jimi.diordie.exception.ResourceNotFoundException
import rocks.jimi.diordie.model.Device
import rocks.jimi.diordie.model.DeviceType
import rocks.jimi.diordie.repository.DeviceRepository
import rocks.jimi.diordie.repository.InstallationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class DeviceServiceImpl(
    private val deviceRepository: DeviceRepository,
    private val installationRepository: InstallationRepository
) : DeviceService {

    override fun getAllDevices(): List<Device> {
        return deviceRepository.findAll()
    }

    override fun getDeviceById(id: Long): Device {
        return deviceRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Device", "id", id) }
    }

    override fun getDevicesByInstallationId(installationId: Long): List<Device> {
        // Verify installation exists
        if (!installationRepository.existsById(installationId)) {
            throw ResourceNotFoundException("Installation", "id", installationId)
        }
        return deviceRepository.findAllByInstallationId(installationId)
    }

    override fun getDevicesByType(type: DeviceType): List<Device> {
        return deviceRepository.findAllByType(type)
    }

    @Transactional
    override fun createDevice(device: Device): Device {
        // Verify installation exists
        val installationId = device.installation.id
            ?: throw IllegalArgumentException("Installation ID cannot be null")
            
        if (!installationRepository.existsById(installationId)) {
            throw ResourceNotFoundException("Installation", "id", installationId)
        }
        
        // Check if device with same serial number already exists
        if (deviceRepository.existsBySerialNumber(device.serialNumber)) {
            throw ResourceAlreadyExistsException("Device", "serialNumber", device.serialNumber)
        }
        
        return deviceRepository.save(device)
    }

    @Transactional
    override fun updateDevice(id: Long, device: Device): Device {
        val existingDevice = getDeviceById(id)
        
        // Verify installation exists
        val installationId = device.installation.id
            ?: throw IllegalArgumentException("Installation ID cannot be null")
            
        if (!installationRepository.existsById(installationId)) {
            throw ResourceNotFoundException("Installation", "id", installationId)
        }
        
        // Check if another device with the same serial number exists
        deviceRepository.findBySerialNumber(device.serialNumber).ifPresent {
            if (it.id != id) {
                throw ResourceAlreadyExistsException("Device", "serialNumber", device.serialNumber)
            }
        }
        
        // Create a new device object with updated fields and the same ID
        val updatedDevice = device.copy(
            id = existingDevice.id,
            createdAt = existingDevice.createdAt,
            updatedAt = LocalDateTime.now()
        )
        
        return deviceRepository.save(updatedDevice)
    }

    @Transactional
    override fun deleteDevice(id: Long) {
        val device = getDeviceById(id)
        deviceRepository.delete(device)
    }
}
