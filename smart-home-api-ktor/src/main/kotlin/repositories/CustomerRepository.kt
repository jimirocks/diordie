package rocks.jimi.diordie.repositories

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import rocks.jimi.diordie.exceptions.ResourceAlreadyExistsException
import rocks.jimi.diordie.exceptions.ResourceNotFoundException
import rocks.jimi.diordie.models.Customer
import rocks.jimi.diordie.models.Customers
import java.time.Instant
import java.util.UUID

class CustomerRepository {

    fun findAll(): List<Customer> = transaction {
        Customers.selectAll().map { it.toCustomer() }
    }

    fun findById(id: UUID): Customer = transaction {
        Customers.select { Customers.id eq id }
            .map { it.toCustomer() }
            .firstOrNull() ?: throw ResourceNotFoundException("Customer", "id", id)
    }

    fun findByCustomerId(customerId: String): Customer = transaction {
        Customers.select { Customers.customerId eq customerId }
            .map { it.toCustomer() }
            .firstOrNull() ?: throw ResourceNotFoundException("Customer", "customerId", customerId)
    }

    fun create(customer: Customer): Customer = transaction {
        // Check if customer with the same customerId already exists
        Customers.select { Customers.customerId eq customer.customerId }
            .firstOrNull()?.let {
                throw ResourceAlreadyExistsException("Customer", "customerId", customer.customerId)
            }

        val id = UUID.randomUUID()
        Customers.insert {
            it[Customers.id] = id
            it[Customers.customerId] = customer.customerId
            it[name] = customer.name
            it[email] = customer.email
            it[phone] = customer.phone
            it[createdAt] = customer.createdAt
            it[updatedAt] = customer.updatedAt
        }

        findById(id)
    }

    fun update(id: UUID, customer: Customer): Customer = transaction {
        val updated = Customers.update({ Customers.id eq id }) {
            it[customerId] = customer.customerId
            it[name] = customer.name
            it[email] = customer.email
            it[phone] = customer.phone
            it[updatedAt] = Instant.now()
        }

        if (updated == 0) {
            throw ResourceNotFoundException("Customer", "id", id)
        }

        findById(id)
    }

    fun delete(id: UUID): Boolean = transaction {
        val deleted = Customers.deleteWhere { Customers.id eq id }
        deleted > 0
    }

    private fun ResultRow.toCustomer(): Customer = Customer(
        id = this[Customers.id].value,
        customerId = this[Customers.customerId],
        name = this[Customers.name],
        email = this[Customers.email],
        phone = this[Customers.phone],
        createdAt = this[Customers.createdAt],
        updatedAt = this[Customers.updatedAt]
    )
}
