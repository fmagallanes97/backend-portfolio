package dev.fmagallanes97.backendportfolio.education.dto.mapper;

import dev.fmagallanes97.backendportfolio.education.Education;
import dev.fmagallanes97.backendportfolio.education.dto.EducationRequest;
import dev.fmagallanes97.backendportfolio.education.dto.EducationResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper extends RequestMapper<Education, EducationRequest>, ResponseMapper<Education, EducationResponse> {
    List<EducationResponse> toResponseList(List<Education> education);
}
