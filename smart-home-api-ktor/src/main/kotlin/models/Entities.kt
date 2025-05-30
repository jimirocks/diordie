package rocks.jimi.diordie.models

import java.time.Instant
import java.util.UUID

data class Customer(
    val id: UUID? = null,
    val customerId: String,
    val name: String,
    val email: String,
    val phone: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

data class Installation(
    val id: UUID? = null,
    val customerId: UUID,
    val address: String,
    val gpsLatitude: Double? = null,
    val gpsLongitude: Double? = null,
    val status: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

data class Device(
    val id: UUID? = null,
    val installationId: UUID,
    val type: DeviceType,
    val serialNumber: String,
    val hwVersion: String,
    val swVersion: String,
    val ipAddress: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)