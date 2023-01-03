package dev.fmagallanes97.backendportfolio.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SkillRequest(
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String name
) {
}
