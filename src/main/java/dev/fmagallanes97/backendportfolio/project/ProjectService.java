package dev.fmagallanes97.backendportfolio.project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public Project findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Project updateById(Long id, Project project) {
        Project probableProject = repository.findById(id).orElseThrow(RuntimeException::new);

        probableProject.setTitle(project.getTitle());
        probableProject.setDescription(project.getDescription());
        probableProject.setGithubRepository(project.getGithubRepository());
        probableProject.setWebsite(project.getWebsite());
        probableProject.setPreviewImage(project.getPreviewImage());
        probableProject.setStartDate(project.getStartDate());
        probableProject.setResume(project.getResume());

        return repository.save(probableProject);
    }

    public void deleteById(Long id) {
        Project probableProject = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableProject);
    }
}
