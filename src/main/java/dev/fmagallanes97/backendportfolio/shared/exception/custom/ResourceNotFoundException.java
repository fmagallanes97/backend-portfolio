package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(Error error) {
        super(error.getStatus(), error.getTitle(), error.getDescription());
    }
}
