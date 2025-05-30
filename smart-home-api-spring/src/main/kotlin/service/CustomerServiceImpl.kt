package rocks.jimi.diordie.service

import com.smarthome.api.exception.ResourceAlreadyExistsException
import com.smarthome.api.exception.ResourceNotFoundException
import com.smarthome.api.model.Customer
import com.smarthome.api.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.Optional

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll()
    }

    override fun getCustomerById(id: Long): Customer {
        return customerRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Customer", "id", id) }
    }

    override fun getCustomerByCustomerId(customerId: String): Optional<Customer> {
        return customerRepository.findByCustomerId(customerId)
    }

    @Transactional
    override fun createCustomer(customer: Customer): Customer {
        // Check if customer with same customerId already exists
        if (customerRepository.existsByCustomerId(customer.customerId)) {
            throw ResourceAlreadyExistsException("Customer", "customerId", customer.customerId)
        }
        
        // Check if customer with same email already exists
        if (customerRepository.existsByEmail(customer.email)) {
            throw ResourceAlreadyExistsException("Customer", "email", customer.email)
        }
        
        return customerRepository.save(customer)
    }

    @Transactional
    override fun updateCustomer(id: Long, customer: Customer): Customer {
        val existingCustomer = getCustomerById(id)
        
        // Check if another customer with the same customerId exists
        customerRepository.findByCustomerId(customer.customerId).ifPresent {
            if (it.id != id) {
                throw ResourceAlreadyExistsException("Customer", "customerId", customer.customerId)
            }
        }
        
        // Create a new customer object with updated fields and the same ID
        val updatedCustomer = customer.copy(
            id = existingCustomer.id,
            createdAt = existingCustomer.createdAt,
            updatedAt = LocalDateTime.now()
        )
        
        return customerRepository.save(updatedCustomer)
    }

    @Transactional
    override fun deleteCustomer(id: Long) {
        val customer = getCustomerById(id)
        customerRepository.delete(customer)
    }
}
