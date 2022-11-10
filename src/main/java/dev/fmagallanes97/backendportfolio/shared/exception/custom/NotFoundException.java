package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.ApiError;

public class NotFoundException extends CustomException {

    public NotFoundException(ApiError error) {
        super(error.getStatus(), error.getDescription());
    }
}
