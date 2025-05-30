package rocks.jimi.diordie.service

import com.smarthome.api.model.Customer
import java.util.Optional

interface CustomerService {
    fun getAllCustomers(): List<Customer>
    fun getCustomerById(id: Long): Customer
    fun getCustomerByCustomerId(customerId: String): Optional<Customer>
    fun createCustomer(customer: Customer): Customer
    fun updateCustomer(id: Long, customer: Customer): Customer
    fun deleteCustomer(id: Long)
}
