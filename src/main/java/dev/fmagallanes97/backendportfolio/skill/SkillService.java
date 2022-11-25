package dev.fmagallanes97.backendportfolio.skill;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillRequest;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillResponse;
import dev.fmagallanes97.backendportfolio.skill.dto.mapper.SkillMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;

    public SkillResponse save(SkillRequest skillRequest) {
        Skill skill = skillMapper.toEntity(skillRequest);

        return skillMapper.toResponse(skillRepository.save(skill));
    }

    public SkillResponse findById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return skillMapper.toResponse(skill);
    }

    public List<SkillResponse> findAllByResumeId(Long resumeId) {
        return skillMapper.toResponseList(skillRepository.findAllByResumeId(resumeId));
    }

    public SkillResponse updateById(Long skillId, SkillRequest skillRequest) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        skillMapper.update(skillRequest, skill);

        return skillMapper.toResponse(skillRepository.save(skill));
    }

    public void deleteById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        skillRepository.delete(skill);
    }
}
