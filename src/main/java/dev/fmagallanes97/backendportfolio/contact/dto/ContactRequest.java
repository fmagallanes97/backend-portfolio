package dev.fmagallanes97.backendportfolio.contact.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record ContactRequest(
        @Email(message = "this value is not a valid email")
        @Size(min = 12, max = 45, message = "this value must be between 12 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String email,
        String githubProfileURL,
        String linkedinProfileURL
) {
}