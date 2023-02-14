package dev.fmagallanes97.backendportfolio.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final Long resourceId;
    private final String resourceName;

    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super("The " + resourceName.toLowerCase() + " with ID " + resourceId + " cannot be found in the database");
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }
}
