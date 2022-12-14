package dev.fmagallanes97.backendportfolio.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PosResponsibilityRequest(
        @Size(min = 12, max = 255, message = "this value must be between 12 and 255 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String description
) {
}
