package dev.fmagallanes97.backendportfolio.project.dto.mapper;

import dev.fmagallanes97.backendportfolio.project.Project;
import dev.fmagallanes97.backendportfolio.project.dto.ProjectRequest;
import dev.fmagallanes97.backendportfolio.project.dto.ProjectResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends RequestMapper<Project, ProjectRequest>, ResponseMapper<Project, ProjectResponse> {
    List<ProjectResponse> toResponseList(List<Project> projects);
}
