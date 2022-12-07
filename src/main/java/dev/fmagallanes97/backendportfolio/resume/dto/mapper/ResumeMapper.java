package dev.fmagallanes97.backendportfolio.resume.dto.mapper;

import dev.fmagallanes97.backendportfolio.contact.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.education.dto.mapper.EducationMapper;
import dev.fmagallanes97.backendportfolio.position.dto.mapper.PositionMapper;
import dev.fmagallanes97.backendportfolio.project.dto.mapper.ProjectMapper;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeRequest;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import dev.fmagallanes97.backendportfolio.skill.dto.mapper.SkillMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ContactMapper.class,
        EducationMapper.class,
        PositionMapper.class,
        ProjectMapper.class,
        SkillMapper.class
})
public interface ResumeMapper extends RequestMapper<Resume, ResumeRequest>, ResponseMapper<Resume, ResumeResponse> {
}
