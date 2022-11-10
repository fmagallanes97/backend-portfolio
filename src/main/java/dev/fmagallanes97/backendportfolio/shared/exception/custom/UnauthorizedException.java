package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.ApiError;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(ApiError error) {
        super(error.getStatus(), error.getDescription());
    }
}
