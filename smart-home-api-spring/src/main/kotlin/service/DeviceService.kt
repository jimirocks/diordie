package rocks.jimi.diordie.service

import rocks.jimi.diordie.model.Device
import rocks.jimi.diordie.model.DeviceType

interface DeviceService {
    fun getAllDevices(): List<Device>
    fun getDeviceById(id: Long): Device
    fun getDevicesByInstallationId(installationId: Long): List<Device>
    fun getDevicesByType(type: DeviceType): List<Device>
    fun createDevice(device: Device): Device
    fun updateDevice(id: Long, device: Device): Device
    fun deleteDevice(id: Long)
}
