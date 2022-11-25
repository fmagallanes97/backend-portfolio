package dev.fmagallanes97.backendportfolio.contact.dto;

public record ContactResponse(
        Long id,
        String email,
        String githubProfileURL,
        String linkedinProfileURL
) {
}