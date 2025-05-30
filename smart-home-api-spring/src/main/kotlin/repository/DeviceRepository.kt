package rocks.jimi.diordie.repository

import com.smarthome.api.model.Device
import com.smarthome.api.model.DeviceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DeviceRepository : JpaRepository<Device, Long> {
    fun findAllByInstallationId(installationId: Long): List<Device>
    fun findBySerialNumber(serialNumber: String): Optional<Device>
    fun existsBySerialNumber(serialNumber: String): Boolean
    fun countByInstallationId(installationId: Long): Long
    fun findAllByType(type: DeviceType): List<Device>
}
