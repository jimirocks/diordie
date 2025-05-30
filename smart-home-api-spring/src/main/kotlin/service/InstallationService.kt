package rocks.jimi.diordie.service

import com.smarthome.api.model.Installation

interface InstallationService {
    fun getAllInstallations(): List<Installation>
    fun getInstallationById(id: Long): Installation
    fun getInstallationsByCustomerId(customerId: Long): List<Installation>
    fun createInstallation(installation: Installation): Installation
    fun updateInstallation(id: Long, installation: Installation): Installation
    fun deleteInstallation(id: Long)
}
