package dev.fmagallanes97.backendportfolio.skill.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.skilltype.SkillType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillResponse {
    private Long id;
    private String name;
    private SkillType type;
    private Resume resume;
}
