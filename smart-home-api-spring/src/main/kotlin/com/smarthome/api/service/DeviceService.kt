package com.smarthome.api.service

import com.smarthome.api.model.Device
import com.smarthome.api.model.DeviceType

interface DeviceService {
    fun getAllDevices(): List<Device>
    fun getDeviceById(id: Long): Device
    fun getDevicesByInstallationId(installationId: Long): List<Device>
    fun getDevicesByType(type: DeviceType): List<Device>
    fun createDevice(device: Device): Device
    fun updateDevice(id: Long, device: Device): Device
    fun deleteDevice(id: Long)
}