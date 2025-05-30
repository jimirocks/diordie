package rocks.jimi.diordie.services

import rocks.jimi.diordie.models.Customer
import rocks.jimi.diordie.repositories.CustomerRepository
import java.util.UUID

class CustomerService(private val customerRepository: CustomerRepository) {
    
    fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll()
    }
    
    fun getCustomerById(id: UUID): Customer {
        return customerRepository.findById(id)
    }
    
    fun getCustomerByCustomerId(customerId: String): Customer {
        return customerRepository.findByCustomerId(customerId)
    }
    
    fun createCustomer(customer: Customer): Customer {
        return customerRepository.create(customer)
    }
    
    fun updateCustomer(id: UUID, customer: Customer): Customer {
        return customerRepository.update(id, customer)
    }
    
    fun deleteCustomer(id: UUID): Boolean {
        return customerRepository.delete(id)
    }
}