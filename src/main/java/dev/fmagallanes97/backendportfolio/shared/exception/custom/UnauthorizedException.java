package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(Error error) {
        super(error.getStatus(), error.getDescription());
    }
}
