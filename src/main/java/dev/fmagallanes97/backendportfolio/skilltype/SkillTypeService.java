package dev.fmagallanes97.backendportfolio.skilltype;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.skilltype.dto.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.skilltype.dto.SkillTypeResponse;
import dev.fmagallanes97.backendportfolio.skilltype.dto.mapper.SkillTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return skillTypeMapper.toResponse(skillType);
    }

    public List<SkillTypeResponse> findAll() {
        return skillTypeMapper.toResponseList(skillTypeRepository.findAll());
    }

    public SkillTypeResponse updateById(Long skillTypeId, SkillTypeRequest skillTypeRequest) {
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        skillTypeMapper.update(skillTypeRequest, skillType);

        return skillTypeMapper.toResponse(skillTypeRepository.save(skillType));
    }

    public void deleteById(Long skillTypeId) {
        SkillType skillType = skillTypeRepository.findById(skillTypeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        skillTypeRepository.delete(skillType);
    }
}
