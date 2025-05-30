package rocks.jimi.diordie.repository

import rocks.jimi.diordie.model.Installation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstallationRepository : JpaRepository<Installation, Long> {
    fun findAllByCustomerId(customerId: Long): List<Installation>
    fun countByCustomerId(customerId: Long): Long
}
