package rocks.jimi.diordie.service

import com.smarthome.api.exception.ResourceNotFoundException
import com.smarthome.api.model.Installation
import com.smarthome.api.repository.CustomerRepository
import com.smarthome.api.repository.InstallationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class InstallationServiceImpl(
    private val installationRepository: InstallationRepository,
    private val customerRepository: CustomerRepository
) : InstallationService {

    override fun getAllInstallations(): List<Installation> {
        return installationRepository.findAll()
    }

    override fun getInstallationById(id: Long): Installation {
        return installationRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Installation", "id", id) }
    }

    override fun getInstallationsByCustomerId(customerId: Long): List<Installation> {
        // Verify customer exists
        if (!customerRepository.existsById(customerId)) {
            throw ResourceNotFoundException("Customer", "id", customerId)
        }
        return installationRepository.findAllByCustomerId(customerId)
    }

    @Transactional
    override fun createInstallation(installation: Installation): Installation {
        // Verify customer exists
        val customerId = installation.customer.id
            ?: throw IllegalArgumentException("Customer ID cannot be null")
            
        if (!customerRepository.existsById(customerId)) {
            throw ResourceNotFoundException("Customer", "id", customerId)
        }
        
        return installationRepository.save(installation)
    }

    @Transactional
    override fun updateInstallation(id: Long, installation: Installation): Installation {
        val existingInstallation = getInstallationById(id)
        
        // Verify customer exists
        val customerId = installation.customer.id
            ?: throw IllegalArgumentException("Customer ID cannot be null")
            
        if (!customerRepository.existsById(customerId)) {
            throw ResourceNotFoundException("Customer", "id", customerId)
        }
        
        // Create a new installation object with updated fields and the same ID
        val updatedInstallation = installation.copy(
            id = existingInstallation.id,
            createdAt = existingInstallation.createdAt,
            updatedAt = LocalDateTime.now()
        )
        
        return installationRepository.save(updatedInstallation)
    }

    @Transactional
    override fun deleteInstallation(id: Long) {
        val installation = getInstallationById(id)
        installationRepository.delete(installation)
    }
}
