package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Project;
import dev.fmagallanes97.backendportfolio.dto.request.ProjectRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends RequestMapper<Project, ProjectRequest>, ResponseMapper<Project, ProjectResponse> {
    List<ProjectResponse> toResponseList(List<Project> projects);
}
