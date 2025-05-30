# Smart Home Installation Inventory API

A Spring Boot application for managing smart home installations, customers, and devices.

## Overview

This API provides inventory management for Loxone-based smart home installations, tracking customers, their installations, and associated devices.

## Technologies Used

- Kotlin 1.9.20
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- OpenAPI/Swagger for API documentation

## Getting Started

### Prerequisites

- JDK 21
- PostgreSQL
- Gradle

### Database Setup

1. Create a PostgreSQL database named `smarthome`:

```sql
CREATE DATABASE smarthome;
```

2. The application will automatically create the necessary tables on startup.

### Running the Application

1. Clone the repository
2. Configure the database connection in `src/main/resources/application.yml` if needed
3. Run the application:

```bash
./gradlew bootRun
```

The application will start on port 8080.

## API Documentation

Once the application is running, you can access the Swagger UI documentation at:

```
http://localhost:8080/swagger-ui.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/api-docs
```

## API Endpoints

### Customers

- `GET /api/customers` - Retrieve all customers
- `GET /api/customers/{id}` - Retrieve a specific customer by ID
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update a customer
- `DELETE /api/customers/{id}` - Delete a customer

### Installations

- `GET /api/installations` - Retrieve all installations
- `GET /api/installations/{id}` - Retrieve a specific installation by ID
- `GET /api/customers/{customerId}/installations` - Retrieve all installations for a specific customer
- `POST /api/installations` - Create a new installation
- `PUT /api/installations/{id}` - Update an installation
- `DELETE /api/installations/{id}` - Delete an installation

### Devices

- `GET /api/devices` - Retrieve all devices
- `GET /api/devices/{id}` - Retrieve a specific device by ID
- `GET /api/installations/{installationId}/devices` - Retrieve all devices for a specific installation
- `GET /api/devices/type/{type}` - Retrieve all devices of a specific type
- `POST /api/devices` - Create a new device
- `PUT /api/devices/{id}` - Update a device
- `DELETE /api/devices/{id}` - Delete a device

## Data Model

### Customer

- `id`: Unique identifier (auto-generated)
- `customerId`: Customer-specific ID (string)
- `name`: Customer name (string)
- `email`: Contact email (string)
- `phone`: Contact phone number (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated

### Installation

- `id`: Unique identifier (auto-generated)
- `customerId`: Reference to customer
- `address`: Physical address (string)
- `gpsLatitude`: GPS latitude coordinate (decimal)
- `gpsLongitude`: GPS longitude coordinate (decimal)
- `status`: Current status of installation (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated

### Device

- `id`: Unique identifier (auto-generated)
- `installationId`: Reference to installation
- `type`: Device type (enum: MINISERVER, ROUTER, ACCESS_POINT, RASPBERRY_PI)
- `serialNumber`: Serial number (string)
- `hwVersion`: Hardware version (string)
- `swVersion`: Software version (string)
- `ipAddress`: IP address (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated