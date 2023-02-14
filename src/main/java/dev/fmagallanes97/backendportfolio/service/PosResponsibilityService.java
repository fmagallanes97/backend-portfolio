package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.PosResponsibilityMapper;
import dev.fmagallanes97.backendportfolio.dto.request.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.dto.response.PosResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.PosResponsibility;
import dev.fmagallanes97.backendportfolio.model.Position;
import dev.fmagallanes97.backendportfolio.repository.PosResponsibilityRepository;
import dev.fmagallanes97.backendportfolio.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.JOB_POSITION;
import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.POS_RESPONSIBILITY;

@Service
@RequiredArgsConstructor
public class PosResponsibilityService {

    private final PosResponsibilityMapper posResponsibilityMapper;
    private final PositionRepository positionRepository;
    private final PosResponsibilityRepository posResponsibilityRepository;

    public PosResponsibilityResponse save(Long positionId, PosResponsibilityRequest responsibilityRequest) {
        Position position = positionRepository.findById(positionId).orElseThrow(() -> new ResourceNotFoundException(JOB_POSITION.getName(), positionId));
        PosResponsibility responsibility = posResponsibilityMapper.toEntity(responsibilityRequest);

        position.addResponsibility(responsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(responsibility));
    }

    public PosResponsibilityResponse findById(Long responsibilityId) {
        PosResponsibility responsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> new ResourceNotFoundException(POS_RESPONSIBILITY.getName(), responsibilityId));
        return posResponsibilityMapper.toResponse(responsibility);
    }

    public List<PosResponsibilityResponse> findAllByPositionId(Long positionId) {
        return posResponsibilityMapper.toResponseList(posResponsibilityRepository.findAllByPositionId(positionId));
    }

    public PosResponsibilityResponse updateById(Long responsibilityId, PosResponsibilityRequest responsibilityRequest) {
        PosResponsibility responsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> new ResourceNotFoundException(POS_RESPONSIBILITY.getName(), responsibilityId));

        posResponsibilityMapper.update(responsibilityRequest, responsibility);

        return posResponsibilityMapper.toResponse(posResponsibilityRepository.save(responsibility));
    }

    public void deleteById(Long responsibilityId) {
        PosResponsibility responsibility = posResponsibilityRepository.findById(responsibilityId).orElseThrow(() -> new ResourceNotFoundException(POS_RESPONSIBILITY.getName(), responsibilityId));
        Long positionId = responsibility.getPosition().getId();
        Position position = positionRepository.findById(positionId).orElseThrow(() -> new ResourceNotFoundException(JOB_POSITION.getName(), positionId));

        position.removeResponsibility(responsibility);

        posResponsibilityRepository.delete(responsibility);
    }
}
