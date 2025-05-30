package rocks.jimi.diordie.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.path
import io.ktor.server.response.*
import rocks.jimi.diordie.exceptions.ResourceAlreadyExistsException
import rocks.jimi.diordie.exceptions.ResourceNotFoundException
import java.time.LocalDateTime

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<ResourceNotFoundException> { call, cause ->
            val errorResponse = ErrorResponse(
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatusCode.NotFound.value,
                error = "Not Found",
                message = cause.message ?: "Resource not found",
                path = call.request.path()
            )
            call.respond(HttpStatusCode.NotFound, errorResponse)
        }
        
        exception<ResourceAlreadyExistsException> { call, cause ->
            val errorResponse = ErrorResponse(
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatusCode.Conflict.value,
                error = "Conflict",
                message = cause.message ?: "Resource already exists",
                path = call.request.path()
            )
            call.respond(HttpStatusCode.Conflict, errorResponse)
        }
        
        exception<IllegalArgumentException> { call, cause ->
            val errorResponse = ErrorResponse(
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatusCode.BadRequest.value,
                error = "Bad Request",
                message = cause.message ?: "Invalid request parameters",
                path = call.request.path()
            )
            call.respond(HttpStatusCode.BadRequest, errorResponse)
        }
        
        exception<Throwable> { call, cause ->
            val errorResponse = ErrorResponse(
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatusCode.InternalServerError.value,
                error = "Internal Server Error",
                message = cause.message ?: "An unexpected error occurred",
                path = call.request.path()
            )
            call.respond(HttpStatusCode.InternalServerError, errorResponse)
        }
    }
}

data class ErrorResponse(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)
