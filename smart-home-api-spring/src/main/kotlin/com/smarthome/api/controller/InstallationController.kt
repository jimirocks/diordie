package com.smarthome.api.controller

import com.smarthome.api.model.Installation
import com.smarthome.api.service.InstallationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Tag(name = "Installation", description = "Installation management APIs")
class InstallationController(private val installationService: InstallationService) {

    @GetMapping("/installations")
    @Operation(
        summary = "Get all installations",
        description = "Retrieve a list of all installations",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved installations")
        ]
    )
    fun getAllInstallations(): ResponseEntity<List<Installation>> {
        return ResponseEntity.ok(installationService.getAllInstallations())
    }

    @GetMapping("/installations/{id}")
    @Operation(
        summary = "Get installation by ID",
        description = "Retrieve a specific installation by its ID",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved installation"),
            ApiResponse(responseCode = "404", description = "Installation not found")
        ]
    )
    fun getInstallationById(@PathVariable id: Long): ResponseEntity<Installation> {
        return ResponseEntity.ok(installationService.getInstallationById(id))
    }

    @GetMapping("/customers/{customerId}/installations")
    @Operation(
        summary = "Get installations by customer ID",
        description = "Retrieve all installations for a specific customer",
        responses = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved installations"),
            ApiResponse(responseCode = "404", description = "Customer not found")
        ]
    )
    fun getInstallationsByCustomerId(@PathVariable customerId: Long): ResponseEntity<List<Installation>> {
        return ResponseEntity.ok(installationService.getInstallationsByCustomerId(customerId))
    }

    @PostMapping("/installations")
    @Operation(
        summary = "Create a new installation",
        description = "Create a new installation with the provided information",
        responses = [
            ApiResponse(responseCode = "201", description = "Installation successfully created"),
            ApiResponse(responseCode = "404", description = "Customer not found")
        ]
    )
    fun createInstallation(@RequestBody installation: Installation): ResponseEntity<Installation> {
        val createdInstallation = installationService.createInstallation(installation)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstallation)
    }

    @PutMapping("/installations/{id}")
    @Operation(
        summary = "Update an installation",
        description = "Update an existing installation with the provided information",
        responses = [
            ApiResponse(responseCode = "200", description = "Installation successfully updated"),
            ApiResponse(responseCode = "404", description = "Installation or customer not found")
        ]
    )
    fun updateInstallation(
        @PathVariable id: Long,
        @RequestBody installation: Installation
    ): ResponseEntity<Installation> {
        return ResponseEntity.ok(installationService.updateInstallation(id, installation))
    }

    @DeleteMapping("/installations/{id}")
    @Operation(
        summary = "Delete an installation",
        description = "Delete an installation by its ID",
        responses = [
            ApiResponse(responseCode = "204", description = "Installation successfully deleted"),
            ApiResponse(responseCode = "404", description = "Installation not found")
        ]
    )
    fun deleteInstallation(@PathVariable id: Long): ResponseEntity<Void> {
        installationService.deleteInstallation(id)
        return ResponseEntity.noContent().build()
    }
}