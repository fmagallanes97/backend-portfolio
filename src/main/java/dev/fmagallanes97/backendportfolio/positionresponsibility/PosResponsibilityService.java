package dev.fmagallanes97.backendportfolio.positionresponsibility;

import dev.fmagallanes97.backendportfolio.position.Position;
import dev.fmagallanes97.backendportfolio.position.PositionRepository;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PosResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.mapper.PosResponsibilityMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosResponsibilityService {

    private final PosResponsibilityMapper posResponsibilityMapper;
    private final PositionRepository positionRepository;
    private final PosResponsibilityRepository posResponsibilityRepository;

    public PosResponsibilityResponse save(Long positionId, PosResponsibilityRequest responsibilityRequest) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        PosResponsibility responsibility = posResponsibilityMapper.toEntity(responsibilityRequest);

        position.addResponsibility(responsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(responsibility));
    }

    public PosResponsibilityResponse findById(Long responsibilityId) {
        PosResponsibility posResponsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return posResponsibilityMapper.toResponse(posResponsibility);
    }

    public List<PosResponsibilityResponse> findAllByPositionId(Long positionId) {
        return posResponsibilityMapper.toResponseList(posResponsibilityRepository.findAllByPositionId(positionId));
    }

    public PosResponsibilityResponse updateById(Long responsibilityId, PosResponsibilityRequest responsibilityRequest) {
        PosResponsibility responsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        posResponsibilityMapper.update(responsibilityRequest, responsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(responsibility));
    }

    public void deleteById(Long responsibilityId) {
        PosResponsibility responsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Position position = positionRepository.findById(responsibility.getPosition().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        position.removeResponsibility(responsibility);

        posResponsibilityRepository.delete(responsibility);
    }
}
