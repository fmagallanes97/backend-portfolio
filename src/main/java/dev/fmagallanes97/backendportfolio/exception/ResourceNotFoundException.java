package dev.fmagallanes97.backendportfolio.exception;

public class ResourceNotFoundException extends CustomException {

    public ResourceNotFoundException(Error error) {
        super(error.getStatus(), error.getTitle(), error.getDescription());
    }
}
