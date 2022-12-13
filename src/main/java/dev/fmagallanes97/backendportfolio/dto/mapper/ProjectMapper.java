package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Project;
import dev.fmagallanes97.backendportfolio.dto.ProjectRequest;
import dev.fmagallanes97.backendportfolio.dto.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends RequestMapper<Project, ProjectRequest>, ResponseMapper<Project, ProjectResponse> {
    List<ProjectResponse> toResponseList(List<Project> projects);
}
