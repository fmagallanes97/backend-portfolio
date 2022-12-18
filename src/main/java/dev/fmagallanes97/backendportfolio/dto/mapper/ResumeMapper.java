package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.dto.request.ResumeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ResumeResponse;
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
