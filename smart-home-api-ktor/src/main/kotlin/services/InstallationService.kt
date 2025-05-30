package rocks.jimi.diordie.services

import rocks.jimi.diordie.models.Installation
import rocks.jimi.diordie.repositories.CustomerRepository
import rocks.jimi.diordie.repositories.InstallationRepository
import java.util.UUID

class InstallationService(
    private val installationRepository: InstallationRepository,
    private val customerRepository: CustomerRepository
) {
    
    fun getAllInstallations(): List<Installation> {
        return installationRepository.findAll()
    }
    
    fun getInstallationById(id: UUID): Installation {
        return installationRepository.findById(id)
    }
    
    fun getInstallationsByCustomerId(customerId: UUID): List<Installation> {
        return installationRepository.findByCustomerId(customerId)
    }
    
    fun getInstallationsByCustomerIdString(customerIdString: String): List<Installation> {
        val customer = customerRepository.findByCustomerId(customerIdString)
        return installationRepository.findByCustomerId(customer.id!!)
    }
    
    fun createInstallation(installation: Installation): Installation {
        // Verify that the customer exists
        customerRepository.findById(installation.customerId)
        return installationRepository.create(installation)
    }
    
    fun updateInstallation(id: UUID, installation: Installation): Installation {
        // Verify that the customer exists
        customerRepository.findById(installation.customerId)
        return installationRepository.update(id, installation)
    }
    
    fun deleteInstallation(id: UUID): Boolean {
        return installationRepository.delete(id)
    }
}