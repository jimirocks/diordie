package rocks.jimi.diordie.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import rocks.jimi.diordie.routes.customerRoutes
import rocks.jimi.diordie.routes.deviceRoutes
import rocks.jimi.diordie.routes.installationRoutes

fun Application.configureRouting() {
    routing {
        route("/api") {
            get {
                call.respondText("Smart Home API is running!")
            }
            
            // Register routes
            customerRoutes()
            installationRoutes()
            deviceRoutes()
        }
    }
}