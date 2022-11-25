package dev.fmagallanes97.backendportfolio.skill.dto;

import dev.fmagallanes97.backendportfolio.skilltype.SkillType;

public record SkillResponse(
        Long id,
        String name,
        SkillType type
) {
}
