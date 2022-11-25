package dev.fmagallanes97.backendportfolio.position;

import dev.fmagallanes97.backendportfolio.position.dto.PositionRequest;
import dev.fmagallanes97.backendportfolio.position.dto.PositionResponse;
import dev.fmagallanes97.backendportfolio.position.dto.mapper.PositionMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;

    public PositionResponse save(PositionRequest positionRequest) {
        Position position = positionMapper.toEntity(positionRequest);

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

        positionRepository.delete(position);
    }
}
