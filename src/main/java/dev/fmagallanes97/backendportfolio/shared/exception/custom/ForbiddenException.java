package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.ApiError;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {

    public ForbiddenException(ApiError error) {
        super(error.getStatus(), error.getDescription());
    }
}
