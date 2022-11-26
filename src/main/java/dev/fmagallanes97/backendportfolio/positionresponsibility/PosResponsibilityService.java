package dev.fmagallanes97.backendportfolio.positionresponsibility;

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
    private final PosResponsibilityRepository posResponsibilityRepository;

    public PosResponsibilityResponse save(PosResponsibilityRequest posResponsibilityRequest) {
        PosResponsibility posResponsibility = posResponsibilityMapper.toEntity(posResponsibilityRequest);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(posResponsibility));
    }

    public PosResponsibilityResponse findById(Long posResponsibilityId) {
        PosResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return posResponsibilityMapper.toResponse(posResponsibility);
    }

    public List<PosResponsibilityResponse> findAllByPositionId(Long positionId) {
        return posResponsibilityMapper.toResponseList(posResponsibilityRepository.findAllByPositionId(positionId));
    }

    public PosResponsibilityResponse updateById(Long posResponsibilityId, PosResponsibilityRequest posResponsibilityRequest) {
        PosResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        posResponsibilityMapper.update(posResponsibilityRequest, posResponsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(posResponsibility));
    }

    public void deleteById(Long posResponsibilityId) {
        PosResponsibility posResponsibility = posResponsibilityRepository.findById(posResponsibilityId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        posResponsibilityRepository.delete(posResponsibility);
    }
}
