package dev.fmagallanes97.backendportfolio.resume;

import dev.fmagallanes97.backendportfolio.resume.dto.ResumeRequest;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeResponse;
import dev.fmagallanes97.backendportfolio.resume.dto.mapper.ResumeMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeMapper resumeMapper;
    private final ResumeRepository resumeRepository;

    public ResumeResponse save(ResumeRequest resumeRequest) {
        Resume resume = resumeMapper.toEntity(resumeRequest);

        return resumeMapper.toResponse(resumeRepository.save(resume));
    }

    public ResumeResponse findById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return resumeMapper.toResponse(resume);
    }

    public ResumeResponse updateById(Long resumeId, ResumeRequest resumeRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resumeMapper.update(resumeRequest, resume);

        return resumeMapper.toResponse(resumeRepository.save(resume));
    }

    public void deleteById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resumeRepository.delete(resume);
    }
}
