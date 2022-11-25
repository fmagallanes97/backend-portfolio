package dev.fmagallanes97.backendportfolio.skill.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record SkillRequest(
        @Size(min = 2, max = 45, message = "this value must be between 2 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String name
) {
}
