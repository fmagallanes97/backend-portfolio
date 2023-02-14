package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.SkillTypeMapper;
import dev.fmagallanes97.backendportfolio.dto.request.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillTypeResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.SkillType;
import dev.fmagallanes97.backendportfolio.repository.SkillTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.SKILL_TYPE;

@Service
@RequiredArgsConstructor
public class SkillTypeService {

    private final SkillTypeMapper skillTypeMapper;
    private final SkillTypeRepository skillTypeRepository;

    public SkillTypeResponse save(SkillTypeRequest skillTypeRequest) {
        SkillType skillType = skillTypeMapper.toEntity(skillTypeRequest);
        return skillTypeMapper.toResponse(skillTypeRepository.save(skillType));
    }

    public SkillTypeResponse findById(Long skillTypeId) {
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> new ResourceNotFoundException(SKILL_TYPE.getName(), skillTypeId));
        return skillTypeMapper.toResponse(skillType);
    }

    public List<SkillTypeResponse> findAll() {
        return skillTypeMapper.toResponseList(skillTypeRepository.findAll());
    }

    public SkillTypeResponse updateById(Long skillTypeId, SkillTypeRequest skillTypeRequest) {
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> new ResourceNotFoundException(SKILL_TYPE.getName(), skillTypeId));

        skillTypeMapper.update(skillTypeRequest, skillType);

        return skillTypeMapper.toResponse(skillTypeRepository.save(skillType));
    }

    public void deleteById(Long skillTypeId) {
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> new ResourceNotFoundException(SKILL_TYPE.getName(), skillTypeId));
        skillTypeRepository.delete(skillType);
    }
}
