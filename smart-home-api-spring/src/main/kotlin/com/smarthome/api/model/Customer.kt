package com.smarthome.api.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(name = "customer_id", nullable = false, unique = true)
    val customerId: String,
    
    @Column(nullable = false)
    val name: String,
    
    @Column(nullable = false)
    val email: String,
    
    @Column(nullable = false)
    val phone: String,
    
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)