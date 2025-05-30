package com.smarthome.api.repository

import com.smarthome.api.model.Installation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstallationRepository : JpaRepository<Installation, Long> {
    fun findAllByCustomerId(customerId: Long): List<Installation>
    fun countByCustomerId(customerId: Long): Long
}