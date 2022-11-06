package dev.fmagallanes97.backendportfolio.resume;

import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final ResumeRepository repository;

    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }

    public Resume save(Resume resume) {
        return repository.save(resume);
    }

    public Resume findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Resume updateById(Long id, Resume resume) {
        Resume probableResume = repository.findById(id).orElseThrow(RuntimeException::new);

        probableResume.setFirstName(resume.getFirstName());
        probableResume.setLastName(resume.getLastName());
        probableResume.setHeadline(resume.getHeadline());
        probableResume.setAbout(resume.getAbout());

        return repository.save(probableResume);
    }

    public void deleteById(Long id) {
        Resume probableResume = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableResume);
    }
}
