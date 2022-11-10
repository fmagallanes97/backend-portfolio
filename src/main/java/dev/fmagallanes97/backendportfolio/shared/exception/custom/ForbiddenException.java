package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;

public class ForbiddenException extends CustomException {

    public ForbiddenException(Error error) {
        super(error.getStatus(), error.getDescription());
    }
}
