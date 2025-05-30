package rocks.jimi.diordie.models

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object Customers : UUIDTable() {
    val customerId = varchar("customer_id", 255).uniqueIndex()
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val phone = varchar("phone", 50)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}

object Installations : UUIDTable() {
    val customerId = reference("customer_id", Customers)
    val address = varchar("address", 255)
    val gpsLatitude = double("gps_latitude").nullable()
    val gpsLongitude = double("gps_longitude").nullable()
    val status = varchar("status", 50)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}

object Devices : UUIDTable() {
    val installationId = reference("installation_id", Installations)
    val type = enumeration<DeviceType>("type")
    val serialNumber = varchar("serial_number", 255)
    val hwVersion = varchar("hw_version", 50)
    val swVersion = varchar("sw_version", 50)
    val ipAddress = varchar("ip_address", 50)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}

enum class DeviceType {
    MINISERVER, ROUTER, ACCESS_POINT, RASPBERRY_PI
}