package dev.fmagallanes97.backendportfolio.resume.dto;

import dev.fmagallanes97.backendportfolio.contact.dto.ContactResponse;
import dev.fmagallanes97.backendportfolio.education.dto.EducationResponse;
import dev.fmagallanes97.backendportfolio.position.dto.PositionResponse;
import dev.fmagallanes97.backendportfolio.project.dto.ProjectResponse;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillResponse;

import java.util.List;

public record ResumeResponse(
        Long id,
        String firstName,
        String lastName,
        String headline,
        String about,
        ContactResponse contact,
        List<EducationResponse> education,
        List<PositionResponse> positions,
        List<ProjectResponse> projects,
        List<SkillResponse> skills
) {
}
