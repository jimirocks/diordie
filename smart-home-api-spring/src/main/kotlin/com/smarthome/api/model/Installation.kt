package com.smarthome.api.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "installations")
data class Installation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,
    
    @Column(nullable = false)
    val address: String,
    
    @Column(name = "gps_latitude", nullable = false)
    val gpsLatitude: Double,
    
    @Column(name = "gps_longitude", nullable = false)
    val gpsLongitude: Double,
    
    @Column(nullable = false)
    val status: String,
    
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)