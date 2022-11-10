package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.ApiError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequest extends CustomException {

    public BadRequest(ApiError error) {
        super(error.getStatus(), error.getDescription());
    }
}
