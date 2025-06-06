package rocks.jimi.diordie.repository

import rocks.jimi.diordie.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByCustomerId(customerId: String): Optional<Customer>
    fun existsByCustomerId(customerId: String): Boolean
    fun existsByEmail(email: String): Boolean
}
