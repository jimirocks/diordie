package rocks.jimi.diordie.controller

import rocks.jimi.diordie.model.Device
import rocks.jimi.diordie.model.DeviceType
import rocks.jimi.diordie.service.DeviceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Tag(name = "Device", description = "Device management APIs")
class DeviceController(private val deviceService: DeviceService) {

    @GetMapping("/devices")
    @Operation(
        summary = "Get all devices",
        description = "Retrieve a list of all devices",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved devices")
        ]
    )
    fun getAllDevices(): ResponseEntity<List<Device>> {
        return ResponseEntity.ok(deviceService.getAllDevices())
    }

    @GetMapping("/devices/{id}")
    @Operation(
        summary = "Get device by ID",
        description = "Retrieve a specific device by its ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved device"),
            ApiResponse(responseCode = "404", description = "Device not found")
        ]
    )
    fun getDeviceById(@PathVariable id: Long): ResponseEntity<Device> {
        return ResponseEntity.ok(deviceService.getDeviceById(id))
    }

    @GetMapping("/installations/{installationId}/devices")
    @Operation(
        summary = "Get devices by installation ID",
        description = "Retrieve all devices for a specific installation",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved devices"),
            ApiResponse(responseCode = "404", description = "Installation not found")
        ]
    )
    fun getDevicesByInstallationId(@PathVariable installationId: Long): ResponseEntity<List<Device>> {
        return ResponseEntity.ok(deviceService.getDevicesByInstallationId(installationId))
    }

    @GetMapping("/devices/type/{type}")
    @Operation(
        summary = "Get devices by type",
        description = "Retrieve all devices of a specific type",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved devices")
        ]
    )
    fun getDevicesByType(@PathVariable type: DeviceType): ResponseEntity<List<Device>> {
        return ResponseEntity.ok(deviceService.getDevicesByType(type))
    }

    @PostMapping("/devices")
    @Operation(
        summary = "Create a new device",
        description = "Create a new device with the provided information",
        responses = [
            ApiResponse(responseCode = "201", description = "Device successfully created"),
            ApiResponse(responseCode = "404", description = "Installation not found"),
            ApiResponse(responseCode = "409", description = "Device with the same serial number already exists")
        ]
    )
    fun createDevice(@RequestBody device: Device): ResponseEntity<Device> {
        val createdDevice = deviceService.createDevice(device)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice)
    }

    @PutMapping("/devices/{id}")
    @Operation(
        summary = "Update a device",
        description = "Update an existing device with the provided information",
        responses = [
            ApiResponse(responseCode = "200", description = "Device successfully updated"),
            ApiResponse(responseCode = "404", description = "Device or installation not found"),
            ApiResponse(responseCode = "409", description = "Device with the same serial number already exists")
        ]
    )
    fun updateDevice(
        @PathVariable id: Long,
        @RequestBody device: Device
    ): ResponseEntity<Device> {
        return ResponseEntity.ok(deviceService.updateDevice(id, device))
    }

    @DeleteMapping("/devices/{id}")
    @Operation(
        summary = "Delete a device",
        description = "Delete a device by its ID",
        responses = [
            ApiResponse(responseCode = "204", description = "Device successfully deleted"),
            ApiResponse(responseCode = "404", description = "Device not found")
        ]
    )
    fun deleteDevice(@PathVariable id: Long): ResponseEntity<Void> {
        deviceService.deleteDevice(id)
        return ResponseEntity.noContent().build()
    }
}
