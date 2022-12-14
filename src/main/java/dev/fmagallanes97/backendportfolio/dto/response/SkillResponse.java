package dev.fmagallanes97.backendportfolio.dto.response;

public record SkillResponse(
        Long id,
        String name,
        SkillTypeResponse type
) {
}
