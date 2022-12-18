package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.repository.EducationRepository;
import dev.fmagallanes97.backendportfolio.dto.request.EducationRequest;
import dev.fmagallanes97.backendportfolio.dto.response.EducationResponse;
import dev.fmagallanes97.backendportfolio.dto.mapper.EducationMapper;
import dev.fmagallanes97.backendportfolio.model.Education;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import dev.fmagallanes97.backendportfolio.exception.Error;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationMapper educationMapper;
    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;

    public EducationResponse save(Long resumeId, EducationRequest educationRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Education education = educationMapper.toEntity(educationRequest);

        resume.addEducation(education);

        return educationMapper.toResponse(education);
    }

    public EducationResponse findById(Long educationId) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return educationMapper.toResponse(education);
    }

    public List<EducationResponse> findAllByResumeId(Long resumeId) {
        return educationMapper.toResponseList(educationRepository.findAllByResumeId(resumeId));
    }

    public EducationResponse updateById(Long educationId, EducationRequest educationRequest) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        educationMapper.update(educationRequest, education);

        return educationMapper.toResponse(educationRepository.save(education));
    }

    public void deleteById(Long educationId) {
        Education education = educationRepository.findById(educationId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Resume resume = resumeRepository.findById(education.getResume().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resume.removeEducation(education);

        educationRepository.delete(education);
    }
}
