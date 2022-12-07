package dev.fmagallanes97.backendportfolio.skill.dto;

import dev.fmagallanes97.backendportfolio.skilltype.dto.SkillTypeResponse;

public record SkillResponse(
        Long id,
        String name,
        SkillTypeResponse type
) {
}
