package dev.fmagallanes97.backendportfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,
            "Validation error",
            "Some values in the specified JSON do not satisfy the conditions expected"),
    INVALID_JSON(HttpStatus.BAD_REQUEST,
            "Invalid JSON",
            "The specified JSON is not syntactically valid"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "Resource not found",
            "The specified resource does not exist");

    private final HttpStatus status;
    private final String title;
    private final String description;
}
