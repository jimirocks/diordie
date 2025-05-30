package rocks.jimi.diordie.plugins

import io.ktor.server.application.*
import rocks.jimi.diordie.config.DatabaseConfig

/**
 * Configures the database connection and initializes the database schema.
 * This function calls the DatabaseConfig.init() function from the config package.
 */
fun Application.configureDatabases() {
    // Initialize the database connection and schema
    DatabaseConfig.init()
}