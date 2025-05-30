package rocks.jimi.diordie.services

import rocks.jimi.diordie.models.Device
import rocks.jimi.diordie.repositories.DeviceRepository
import rocks.jimi.diordie.repositories.InstallationRepository
import java.util.UUID

class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val installationRepository: InstallationRepository
) {
    
    fun getAllDevices(): List<Device> {
        return deviceRepository.findAll()
    }
    
    fun getDeviceById(id: UUID): Device {
        return deviceRepository.findById(id)
    }
    
    fun getDevicesByInstallationId(installationId: UUID): List<Device> {
        return deviceRepository.findByInstallationId(installationId)
    }
    
    fun createDevice(device: Device): Device {
        // Verify that the installation exists
        installationRepository.findById(device.installationId)
        return deviceRepository.create(device)
    }
    
    fun updateDevice(id: UUID, device: Device): Device {
        // Verify that the installation exists
        installationRepository.findById(device.installationId)
        return deviceRepository.update(id, device)
    }
    
    fun deleteDevice(id: UUID): Boolean {
        return deviceRepository.delete(id)
    }
}