package com.smarthome.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ResourceAlreadyExistsException(resourceName: String, fieldName: String, fieldValue: Any) : 
    RuntimeException("$resourceName already exists with $fieldName: '$fieldValue'")