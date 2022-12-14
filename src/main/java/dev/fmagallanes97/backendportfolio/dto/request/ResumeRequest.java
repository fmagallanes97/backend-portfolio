package dev.fmagallanes97.backendportfolio.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ResumeRequest(
        @Size(min = 2, max = 45, message = "this value must be between 2 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String firstName,
        @Size(min = 2, max = 45, message = "this value must be between 2 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String lastName,
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String headline,
        @Size(min = 20, max = 255, message = "this value must be between 20 and 255 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String about
) {
}
