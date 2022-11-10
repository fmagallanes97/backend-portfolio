package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;

public class BadRequest extends CustomException {

    public BadRequest(Error error) {
        super(error.getStatus(), error.getDescription());
    }
}
