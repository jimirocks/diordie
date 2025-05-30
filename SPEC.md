# Smart Home Installation Inventory REST API

## 1. Overview
This API provides inventory management for Loxone-based smart home installations, tracking customers, their installations, and associated devices.

## 2. Data Model

### 2.1 Customer
- `id`: Unique identifier (auto-generated)
- `customerId`: Customer-specific ID (string)
- `name`: Customer name (string)
- `email`: Contact email (string)
- `phone`: Contact phone number (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated

### 2.2 Installation
- `id`: Unique identifier (auto-generated)
- `customerId`: Reference to customer
- `address`: Physical address (string)
- `gpsLatitude`: GPS latitude coordinate (decimal)
- `gpsLongitude`: GPS longitude coordinate (decimal)
- `status`: Current status of installation (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated

### 2.3 Device
- `id`: Unique identifier (auto-generated)
- `installationId`: Reference to installation
- `type`: Device type (enum: MINISERVER, ROUTER, ACCESS_POINT, RASPBERRY_PI)
- `serialNumber`: Serial number (string)
- `hwVersion`: Hardware version (string)
- `swVersion`: Software version (string)
- `ipAddress`: IP address (string)
- `createdAt`: Timestamp when record was created
- `updatedAt`: Timestamp when record was last updated

## 3. API Endpoints

### 3.1 Customers

#### GET /api/customers
- Description: Retrieve all customers
- Response: 200 OK with array of customer objects

#### GET /api/customers/{id}
- Description: Retrieve a specific customer by ID
- Response: 200 OK with customer object or 404 Not Found

#### POST /api/customers
- Description: Create a new customer
- Request Body: Customer object (without id)
- Response: 201 Created with the created customer object

#### PUT /api/customers/{id}
- Description: Update a customer
- Request Body: Customer object
- Response: 200 OK with updated customer or 404 Not Found

#### DELETE /api/customers/{id}
- Description: Delete a customer
- Response: 204 No Content or 404 Not Found

### 3.2 Installations

#### GET /api/installations
- Description: Retrieve all installations
- Response: 200 OK with array of installation objects

#### GET /api/installations/{id}
- Description: Retrieve a specific installation by ID
- Response: 200 OK with installation object or 404 Not Found

#### GET /api/customers/{customerId}/installations
- Description: Retrieve all installations for a specific customer
- Response: 200 OK with array of installation objects

#### POST /api/installations
- Description: Create a new installation
- Request Body: Installation object (without id)
- Response: 201 Created with the created installation object

#### PUT /api/installations/{id}
- Description: Update an installation
- Request Body: Installation object
- Response: 200 OK with updated installation or 404 Not Found

#### DELETE /api/installations/{id}
- Description: Delete an installation
- Response: 204 No Content or 404 Not Found

### 3.3 Devices

#### GET /api/devices
- Description: Retrieve all devices
- Response: 200 OK with array of device objects

#### GET /api/devices/{id}
- Description: Retrieve a specific device by ID
- Response: 200 OK with device object or 404 Not Found

#### GET /api/installations/{installationId}/devices
- Description: Retrieve all devices for a specific installation
- Response: 200 OK with array of device objects

#### POST /api/devices
- Description: Create a new device
- Request Body: Device object (without id)
- Response: 201 Created with the created device object

#### PUT /api/devices/{id}
- Description: Update a device
- Request Body: Device object
- Response: 200 OK with updated device or 404 Not Found

#### DELETE /api/devices/{id}
- Description: Delete a device
- Response: 204 No Content or 404 Not Found

## 4. Technical Implementation

### 4.1 Technology Stack
- Language: Java 21
- Database: PostgreSQL
- API Documentation: OpenAPI/Swagger

### 4.2 Database Schema
The database schema will follow the data model described above, with appropriate primary keys, foreign keys, and indexes.

### 4.3 Logging
- API requests and responses
- Error conditions
- Performance metrics

### 4.4 Security
- First iteration: No authentication (to be added in future versions)
- Basic input validation to prevent malicious data

## 5. Future Considerations
- Authentication and authorization
- Search and filtering capabilities
- Pagination for large data sets
- Integration with external systems
- Advanced reporting features