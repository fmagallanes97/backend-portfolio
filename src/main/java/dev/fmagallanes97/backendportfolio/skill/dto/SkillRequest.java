package dev.fmagallanes97.backendportfolio.skill.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SkillRequest {
    @NotEmpty(message = "Name attribute is mandatory.")
    private String name;
}
