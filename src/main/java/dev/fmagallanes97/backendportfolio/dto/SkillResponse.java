package dev.fmagallanes97.backendportfolio.dto;

public record SkillResponse(
        Long id,
        String name,
        SkillTypeResponse type
) {
}
