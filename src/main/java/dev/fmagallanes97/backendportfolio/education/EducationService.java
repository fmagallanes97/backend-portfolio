package dev.fmagallanes97.backendportfolio.education;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository repository;

    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public Education save(Education education) {
        return repository.save(education);
    }

    public Education findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Education> findAllByResumeId(Long id) {
        return repository.findAllByResumeId(id);
    }

    public Education updateById(Long id, Education education) {
        Education probableEducation = repository.findById(id).orElseThrow(RuntimeException::new);

        probableEducation.setDegree(education.getDegree());
        probableEducation.setSchool(education.getSchool());
        probableEducation.setAcademicField(education.getAcademicField());
        probableEducation.setStartDate(education.getStartDate());
        probableEducation.setEndDate(education.getEndDate());
        probableEducation.setResume(education.getResume());

        return repository.save(probableEducation);
    }

    public void deleteById(Long id) {
        Education probableEducation = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableEducation);
    }
}
