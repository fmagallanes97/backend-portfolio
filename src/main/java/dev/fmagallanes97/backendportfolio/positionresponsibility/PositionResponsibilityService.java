package dev.fmagallanes97.backendportfolio.positionresponsibility;

import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PositionResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PositionResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.mapper.PositionResponsibilityMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionResponsibilityService {

    private final PositionResponsibilityMapper posResponsibilityMapper;
    private final PositionResponsibilityRepository posResponsibilityRepository;

    public PositionResponsibilityResponse save(PositionResponsibilityRequest posResponsibilityRequest) {
        PositionResponsibility posResponsibility = posResponsibilityMapper.toEntity(posResponsibilityRequest);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(posResponsibility));
    }

    public PositionResponsibilityResponse findById(Long posResponsibilityId) {
        PositionResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return posResponsibilityMapper.toResponse(posResponsibility);
    }

    public List<PositionResponsibilityResponse> findAllByPositionId(Long positionId) {
        return posResponsibilityMapper.toResponseList(posResponsibilityRepository.findAllByPositionId(positionId));
    }

    public PositionResponsibilityResponse updateById(Long posResponsibilityId, PositionResponsibilityRequest posResponsibilityRequest) {
        PositionResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        posResponsibilityMapper.update(posResponsibilityRequest, posResponsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(posResponsibility));
    }

    public void deleteById(Long posResponsibilityId) {
        PositionResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        posResponsibilityRepository.delete(posResponsibility);
    }
}
