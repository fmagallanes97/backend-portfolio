package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.EducationMapper;
import dev.fmagallanes97.backendportfolio.dto.request.EducationRequest;
import dev.fmagallanes97.backendportfolio.dto.response.EducationResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.Education;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.EducationRepository;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.EDUCATION;
import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.RESUME;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationMapper educationMapper;
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;

    public EducationResponse save(Long resumeId, EducationRequest educationRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        Education education = educationMapper.toEntity(educationRequest);

        resume.addEducation(education);

        return educationMapper.toResponse(education);
    }

    public EducationResponse findById(Long educationId) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> new ResourceNotFoundException(EDUCATION.getName(), educationId));
        return educationMapper.toResponse(education);
    }

    public List<EducationResponse> findAllByResumeId(Long resumeId) {
        return educationMapper.toResponseList(educationRepository.findAllByResumeId(resumeId));
    }

    public EducationResponse updateById(Long educationId, EducationRequest educationRequest) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> new ResourceNotFoundException(EDUCATION.getName(), educationId));

        educationMapper.update(educationRequest, education);

        return educationMapper.toResponse(educationRepository.save(education));
    }

    public void deleteById(Long educationId) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> new ResourceNotFoundException(EDUCATION.getName(), educationId));
        Long resumeId = education.getResume().getId();
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));

        resume.removeEducation(education);

        educationRepository.delete(education);
    }
}
