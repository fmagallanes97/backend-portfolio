package dev.fmagallanes97.backendportfolio.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record PosResponsibilityRequest(
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String description
) {
}
