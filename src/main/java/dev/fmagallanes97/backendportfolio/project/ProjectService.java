package dev.fmagallanes97.backendportfolio.project;

import dev.fmagallanes97.backendportfolio.project.dto.ProjectRequest;
import dev.fmagallanes97.backendportfolio.project.dto.ProjectResponse;
import dev.fmagallanes97.backendportfolio.project.dto.mapper.ProjectMapper;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.ResumeRepository;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ResumeRepository resumeRepository;
    private final ProjectRepository projectRepository;

    public ProjectResponse save(Long resumeId, ProjectRequest projectRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Project project = projectMapper.toEntity(projectRequest);

        resume.addProject(project);

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public ProjectResponse findById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return projectMapper.toResponse(project);
    }

    public List<ProjectResponse> findAllByResumeId(Long resumeId) {
        return projectMapper.toResponseList(projectRepository.findAllByResumeId(resumeId));
    }

    public ProjectResponse updateById(Long projectId, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        projectMapper.update(projectRequest, project);

        return projectMapper.toResponse(projectRepository.save(project));
    }

    public void deleteById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Resume resume = resumeRepository.findById(project.getResume().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resume.removeProject(project);

        projectRepository.delete(project);
    }
}
