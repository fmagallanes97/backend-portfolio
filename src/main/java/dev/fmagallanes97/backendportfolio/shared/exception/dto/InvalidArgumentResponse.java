package dev.fmagallanes97.backendportfolio.shared.exception.dto;

public record InvalidArgumentResponse(
        String name,
        String reason
) {
}
