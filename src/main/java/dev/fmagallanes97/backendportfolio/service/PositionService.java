package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.model.Position;
import dev.fmagallanes97.backendportfolio.repository.PositionRepository;
import dev.fmagallanes97.backendportfolio.dto.request.PositionRequest;
import dev.fmagallanes97.backendportfolio.dto.response.PositionResponse;
import dev.fmagallanes97.backendportfolio.dto.mapper.PositionMapper;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import dev.fmagallanes97.backendportfolio.exception.Error;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionMapper positionMapper;
    private final ResumeRepository resumeRepository;
    private final PositionRepository positionRepository;

    public PositionResponse save(Long resumeId, PositionRequest positionRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Position position = positionMapper.toEntity(positionRequest);

        resume.addPosition(position);

        return positionMapper.toResponse(positionRepository.save(position));
    }

    public PositionResponse findById(Long positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return positionMapper.toResponse(position);
    }

    public List<PositionResponse> findAllByResumeId(Long resumeId) {
        return positionMapper.toResponseList(positionRepository.findAllByResumeId(resumeId));
    }

    public PositionResponse updateById(Long positionId, PositionRequest positionRequest) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        positionMapper.update(positionRequest, position);

        return positionMapper.toResponse(positionRepository.save(position));
    }

    public void deleteById(Long positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Resume resume = resumeRepository.findById(position.getResume().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resume.removePosition(position);

        positionRepository.delete(position);
    }
}
