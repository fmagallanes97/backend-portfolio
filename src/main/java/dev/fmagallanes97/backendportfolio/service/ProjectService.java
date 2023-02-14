package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.ProjectMapper;
import dev.fmagallanes97.backendportfolio.dto.request.ProjectRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ProjectResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.Project;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ProjectRepository;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.PROJECT;
import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.RESUME;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ResumeRepository resumeRepository;
    private final ProjectRepository projectRepository;

    public ProjectResponse save(Long resumeId, ProjectRequest projectRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        Project project = projectMapper.toEntity(projectRequest);

        resume.addProject(project);

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public ProjectResponse findById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException(PROJECT.getName(), projectId));
        return projectMapper.toResponse(project);
    }

    public List<ProjectResponse> findAllByResumeId(Long resumeId) {
        return projectMapper.toResponseList(projectRepository.findAllByResumeId(resumeId));
    }

    public ProjectResponse updateById(Long projectId, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException(PROJECT.getName(), projectId));

        projectMapper.update(projectRequest, project);

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public void deleteById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException(PROJECT.getName(), projectId));
        Long resumeId = project.getResume().getId();
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));

        resume.removeProject(project);

        projectRepository.delete(project);
    }
}
