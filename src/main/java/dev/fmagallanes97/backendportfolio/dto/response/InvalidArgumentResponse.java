package dev.fmagallanes97.backendportfolio.dto.response;

public record InvalidArgumentResponse(
        String name,
        String reason
) {
}
