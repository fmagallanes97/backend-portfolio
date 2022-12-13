package dev.fmagallanes97.backendportfolio.dto;

public record InvalidArgumentResponse(
        String name,
        String reason
) {
}
