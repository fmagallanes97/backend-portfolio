package dev.fmagallanes97.backendportfolio.dto;

public record ContactResponse(
        Long id,
        String email,
        String githubProfileURL,
        String linkedinProfileURL
) {
}