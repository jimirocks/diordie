package rocks.jimi.diordie

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import rocks.jimi.diordie.di.appModule
import rocks.jimi.diordie.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Install Koin for dependency injection
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    // Configure plugins
    configureRouting()
    configureSerialization()
    configureStatusPages()
    configureMonitoring()
    configureCORS()
    configureSwagger()

    // Initialize database
    configureDatabases()

    monitor.subscribe(ApplicationStarted) { application ->
        log.info("Application XXX: ${application.environment.config.property("ktor.environment")}")
    }
}
