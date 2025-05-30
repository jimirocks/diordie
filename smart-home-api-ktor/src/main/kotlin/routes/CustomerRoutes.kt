package rocks.jimi.diordie.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import rocks.jimi.diordie.models.CustomerDTO
import rocks.jimi.diordie.models.toDTO
import rocks.jimi.diordie.models.toEntity
import rocks.jimi.diordie.services.CustomerService
import java.util.*

fun Route.customerRoutes() {
    val customerService: CustomerService by inject()

    route("/customers") {
        // Get all customers
        get {
            val customers = customerService.getAllCustomers().map { it.toDTO() }
            call.respond(customers)
        }

        // Get customer by ID
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val customer = customerService.getCustomerById(UUID.fromString(id)).toDTO()
                call.respond(customer)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found")
            }
        }

        // Get customer by customer ID
        get("/by-customer-id/{customerId}") {
            val customerId = call.parameters["customerId"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing customer id")
            try {
                val customer = customerService.getCustomerByCustomerId(customerId).toDTO()
                call.respond(customer)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found")
            }
        }

        // Create new customer
        post {
            val customerDTO = call.receive<CustomerDTO>()
            try {
                val createdCustomer = customerService.createCustomer(customerDTO.toEntity()).toDTO()
                call.respond(HttpStatusCode.Created, createdCustomer)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Failed to create customer")
            }
        }

        // Update customer
        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val customerDTO = call.receive<CustomerDTO>()
            try {
                val updatedCustomer = customerService.updateCustomer(UUID.fromString(id), customerDTO.toEntity()).toDTO()
                call.respond(updatedCustomer)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found")
            }
        }

        // Delete customer
        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            try {
                val deleted = customerService.deleteCustomer(UUID.fromString(id))
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Customer not found")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound, "Customer not found")
            }
        }
    }
}