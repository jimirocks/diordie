package com.smarthome.api.controller

import com.smarthome.api.model.Customer
import com.smarthome.api.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer management APIs")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    @Operation(
        summary = "Get all customers",
        description = "Retrieve a list of all customers",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved customers")
        ]
    )
    fun getAllCustomers(): ResponseEntity<List<Customer>> {
        return ResponseEntity.ok(customerService.getAllCustomers())
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get customer by ID",
        description = "Retrieve a specific customer by their ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved customer"),
            ApiResponse(responseCode = "404", description = "Customer not found")
        ]
    )
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer> {
        return ResponseEntity.ok(customerService.getCustomerById(id))
    }

    @PostMapping
    @Operation(
        summary = "Create a new customer",
        description = "Create a new customer with the provided information",
        responses = [
            ApiResponse(responseCode = "201", description = "Customer successfully created"),
            ApiResponse(responseCode = "409", description = "Customer with the same ID or email already exists")
        ]
    )
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val createdCustomer = customerService.createCustomer(customer)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer)
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update a customer",
        description = "Update an existing customer with the provided information",
        responses = [
            ApiResponse(responseCode = "200", description = "Customer successfully updated"),
            ApiResponse(responseCode = "404", description = "Customer not found"),
            ApiResponse(responseCode = "409", description = "Customer with the same ID or email already exists")
        ]
    )
    fun updateCustomer(
        @PathVariable id: Long,
        @RequestBody customer: Customer
    ): ResponseEntity<Customer> {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer))
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a customer",
        description = "Delete a customer by their ID",
        responses = [
            ApiResponse(responseCode = "204", description = "Customer successfully deleted"),
            ApiResponse(responseCode = "404", description = "Customer not found")
        ]
    )
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Void> {
        customerService.deleteCustomer(id)
        return ResponseEntity.noContent().build()
    }
}