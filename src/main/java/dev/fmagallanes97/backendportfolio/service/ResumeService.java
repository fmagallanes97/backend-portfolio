package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.ResumeMapper;
import dev.fmagallanes97.backendportfolio.dto.request.ResumeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ResumeResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.RESUME;

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
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        return resumeMapper.toResponse(resume);
    }

    public ResumeResponse updateById(Long resumeId, ResumeRequest resumeRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));

        resumeMapper.update(resumeRequest, resume);

        return resumeMapper.toResponse(resumeRepository.save(resume));
    }

    public void deleteById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        resumeRepository.delete(resume);
    }
}
