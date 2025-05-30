package rocks.jimi.diordie.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import rocks.jimi.diordie.models.DeviceDTO
import rocks.jimi.diordie.models.toDTO
import rocks.jimi.diordie.models.toEntity
import rocks.jimi.diordie.services.DeviceService
import java.util.*

fun Route.deviceRoutes() {
    val deviceService: DeviceService by inject()

    route("/devices") {
        // Get all devices
        get {
            val devices = deviceService.getAllDevices().map { it.toDTO() }
            call.respond(devices)
        }

        // Get device by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val device = deviceService.getDeviceById(UUID.fromString(id)).toDTO()
                call.respond(device)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Device not found")
            }
        }

        // Get devices by installation ID
        get("/by-installation/{installationId}") {
            val installationId = call.parameters["installationId"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing installation id")
            try {
                val devices = deviceService.getDevicesByInstallationId(UUID.fromString(installationId)).map { it.toDTO() }
                call.respond(devices)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Installation not found or no devices found")
            }
        }

        // Create new device
        post {
            val deviceDTO = call.receive<DeviceDTO>()
            try {
                val createdDevice = deviceService.createDevice(deviceDTO.toEntity()).toDTO()
                call.respond(HttpStatusCode.Created, createdDevice)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Failed to create device")
            }
        }

        // Update device
        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val deviceDTO = call.receive<DeviceDTO>()
            try {
                val updatedDevice = deviceService.updateDevice(UUID.fromString(id), deviceDTO.toEntity()).toDTO()
                call.respond(updatedDevice)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Device not found")
            }
        }

        // Delete device
        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val deleted = deviceService.deleteDevice(UUID.fromString(id))
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Device not found")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Device not found")
            }
        }
    }
}