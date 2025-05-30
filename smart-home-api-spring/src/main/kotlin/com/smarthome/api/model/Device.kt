package com.smarthome.api.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "devices")
data class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id", nullable = false)
    val installation: Installation,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: DeviceType,
    
    @Column(name = "serial_number", nullable = false, unique = true)
    val serialNumber: String,
    
    @Column(name = "hw_version", nullable = false)
    val hwVersion: String,
    
    @Column(name = "sw_version", nullable = false)
    val swVersion: String,
    
    @Column(name = "ip_address", nullable = false)
    val ipAddress: String,
    
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)