package dev.fmagallanes97.backendportfolio.dto.response;

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
