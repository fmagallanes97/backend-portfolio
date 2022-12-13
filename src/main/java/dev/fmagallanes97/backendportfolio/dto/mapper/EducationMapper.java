package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Education;
import dev.fmagallanes97.backendportfolio.dto.EducationRequest;
import dev.fmagallanes97.backendportfolio.dto.EducationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper extends RequestMapper<Education, EducationRequest>, ResponseMapper<Education, EducationResponse> {
    List<EducationResponse> toResponseList(List<Education> education);
}
