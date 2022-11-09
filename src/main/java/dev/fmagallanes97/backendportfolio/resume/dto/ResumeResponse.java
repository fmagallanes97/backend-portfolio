package dev.fmagallanes97.backendportfolio.resume.dto;

import dev.fmagallanes97.backendportfolio.education.Education;
import dev.fmagallanes97.backendportfolio.position.Position;
import dev.fmagallanes97.backendportfolio.project.Project;
import dev.fmagallanes97.backendportfolio.skill.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResumeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String headline;
    private String about;
    private List<Education> educations;
    private List<Position> positions;
    private List<Project> projects;
    private List<Skill> skills;
}
