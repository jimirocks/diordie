package rocks.jimi.diordie.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import rocks.jimi.diordie.models.InstallationDTO
import rocks.jimi.diordie.models.toDTO
import rocks.jimi.diordie.models.toEntity
import rocks.jimi.diordie.services.InstallationService
import java.util.*

fun Route.installationRoutes() {
    val installationService: InstallationService by inject()

    route("/installations") {
        // Get all installations
        get {
            val installations = installationService.getAllInstallations().map { it.toDTO() }
            call.respond(installations)
        }

        // Get installation by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val installation = installationService.getInstallationById(UUID.fromString(id)).toDTO()
                call.respond(installation)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Installation not found")
            }
        }

        // Get installations by customer ID
        get("/by-customer/{customerId}") {
            val customerId = call.parameters["customerId"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing customer id")
            try {
                val installations = installationService.getInstallationsByCustomerId(UUID.fromString(customerId)).map { it.toDTO() }
                call.respond(installations)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found or no installations found")
            }
        }

        // Get installations by customer ID string
        get("/by-customer-id/{customerIdString}") {
            val customerIdString = call.parameters["customerIdString"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing customer id string")
            try {
                val installations = installationService.getInstallationsByCustomerIdString(customerIdString).map { it.toDTO() }
                call.respond(installations)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found or no installations found")
            }
        }

        // Create new installation
        post {
            val installationDTO = call.receive<InstallationDTO>()
            try {
                val createdInstallation = installationService.createInstallation(installationDTO.toEntity()).toDTO()
                call.respond(HttpStatusCode.Created, createdInstallation)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Failed to create installation")
            }
        }

        // Update installation
        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val installationDTO = call.receive<InstallationDTO>()
            try {
                val updatedInstallation = installationService.updateInstallation(UUID.fromString(id), installationDTO.toEntity()).toDTO()
                call.respond(updatedInstallation)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Installation not found")
            }
        }

        // Delete installation
        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val deleted = installationService.deleteInstallation(UUID.fromString(id))
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Installation not found")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Installation not found")
            }
        }
    }
}