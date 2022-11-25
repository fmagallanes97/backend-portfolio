package dev.fmagallanes97.backendportfolio.education;

import dev.fmagallanes97.backendportfolio.education.dto.EducationRequest;
import dev.fmagallanes97.backendportfolio.education.dto.EducationResponse;
import dev.fmagallanes97.backendportfolio.education.dto.mapper.EducationMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationMapper educationMapper;
    private final EducationRepository educationRepository;

    public EducationResponse save(EducationRequest educationRequest) {
        Education education = educationMapper.toEntity(educationRequest);

        return educationMapper.toResponse(educationRepository.save(education));
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

        educationRepository.delete(education);
    }
}
