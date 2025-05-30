package rocks.jimi.diordie.models

import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class CustomerDTO(
    val id: String? = null,
    val customerId: String,
    val name: String,
    val email: String,
    val phone: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

@Serializable
data class InstallationDTO(
    val id: String? = null,
    val customerId: String,
    val address: String,
    val gpsLatitude: Double? = null,
    val gpsLongitude: Double? = null,
    val status: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

@Serializable
data class DeviceDTO(
    val id: String? = null,
    val installationId: String,
    val type: DeviceTypeDTO,
    val serialNumber: String,
    val hwVersion: String,
    val swVersion: String,
    val ipAddress: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

@Serializable
enum class DeviceTypeDTO {
    MINISERVER, ROUTER, ACCESS_POINT, RASPBERRY_PI
}

// Extension functions to convert between domain models and DTOs
fun CustomerDTO.toEntity(): Customer = Customer(
    id = this.id?.let { UUID.fromString(it) },
    customerId = this.customerId,
    name = this.name,
    email = this.email,
    phone = this.phone,
    createdAt = this.createdAt?.let { Instant.parse(it) } ?: Instant.now(),
    updatedAt = this.updatedAt?.let { Instant.parse(it) } ?: Instant.now()
)

fun Customer.toDTO(): CustomerDTO = CustomerDTO(
    id = this.id.toString(),
    customerId = this.customerId,
    name = this.name,
    email = this.email,
    phone = this.phone,
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)

fun InstallationDTO.toEntity(): Installation = Installation(
    id = this.id?.let { UUID.fromString(it) },
    customerId = UUID.fromString(this.customerId),
    address = this.address,
    gpsLatitude = this.gpsLatitude,
    gpsLongitude = this.gpsLongitude,
    status = this.status,
    createdAt = this.createdAt?.let { Instant.parse(it) } ?: Instant.now(),
    updatedAt = this.updatedAt?.let { Instant.parse(it) } ?: Instant.now()
)

fun Installation.toDTO(): InstallationDTO = InstallationDTO(
    id = this.id.toString(),
    customerId = this.customerId.toString(),
    address = this.address,
    gpsLatitude = this.gpsLatitude,
    gpsLongitude = this.gpsLongitude,
    status = this.status,
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)

fun DeviceDTO.toEntity(): Device = Device(
    id = this.id?.let { UUID.fromString(it) },
    installationId = UUID.fromString(this.installationId),
    type = DeviceType.valueOf(this.type.name),
    serialNumber = this.serialNumber,
    hwVersion = this.hwVersion,
    swVersion = this.swVersion,
    ipAddress = this.ipAddress,
    createdAt = this.createdAt?.let { Instant.parse(it) } ?: Instant.now(),
    updatedAt = this.updatedAt?.let { Instant.parse(it) } ?: Instant.now()
)

fun Device.toDTO(): DeviceDTO = DeviceDTO(
    id = this.id.toString(),
    installationId = this.installationId.toString(),
    type = DeviceTypeDTO.valueOf(this.type.name),
    serialNumber = this.serialNumber,
    hwVersion = this.hwVersion,
    swVersion = this.swVersion,
    ipAddress = this.ipAddress,
    createdAt = this.createdAt.toString(),
    updatedAt = this.updatedAt.toString()
)