package dev.fmagallanes97.backendportfolio.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found. Please, check the URI."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes with wrong values. Please, review the following."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "The request does not have the format expected."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please try again later."),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "Sorry, you do not have permission to access this resource."),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "Sorry, you do not have privileges to access this resource.");

    private final HttpStatus status;
    private final String description;
}
