package rocks.jimi.diordie.exceptions

class ResourceAlreadyExistsException(resourceName: String, fieldName: String, fieldValue: Any) : 
    RuntimeException("$resourceName already exists with $fieldName: '$fieldValue'")