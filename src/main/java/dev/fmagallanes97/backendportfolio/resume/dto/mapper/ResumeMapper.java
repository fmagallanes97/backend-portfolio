package dev.fmagallanes97.backendportfolio.resume.dto.mapper;

import dev.fmagallanes97.backendportfolio.contact.Contact;
import dev.fmagallanes97.backendportfolio.contact.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.education.Education;
import dev.fmagallanes97.backendportfolio.education.dto.mapper.EducationMapper;
import dev.fmagallanes97.backendportfolio.position.Position;
import dev.fmagallanes97.backendportfolio.position.dto.mapper.PositionMapper;
import dev.fmagallanes97.backendportfolio.project.Project;
import dev.fmagallanes97.backendportfolio.project.dto.mapper.ProjectMapper;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeRequest;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.skill.Skill;
import dev.fmagallanes97.backendportfolio.skill.dto.mapper.SkillMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ContactMapper.class,
        EducationMapper.class,
        PositionMapper.class,
        ProjectMapper.class,
        SkillMapper.class
})
public interface ResumeMapper extends RequestMapper<Resume, ResumeRequest> {

    @Mapping(source = "resume.id", target = "id")
    ResumeResponse toResponse(
            Resume resume,
            Contact contact,
            List<Education> education,
            List<Position> positions,
            List<Project> projects,
            List<Skill> skills
    );

    ResumeResponse toResponse(Resume resume);
}
