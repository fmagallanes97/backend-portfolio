package dev.fmagallanes97.backendportfolio.skill;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.ResumeRepository;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillRequest;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillResponse;
import dev.fmagallanes97.backendportfolio.skill.dto.mapper.SkillMapper;
import dev.fmagallanes97.backendportfolio.skilltype.SkillType;
import dev.fmagallanes97.backendportfolio.skilltype.SkillTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillMapper skillMapper;
    private final ResumeRepository resumeRepository;
    private final SkillTypeRepository skillTypeRepository;
    private final SkillRepository skillRepository;

    public SkillResponse save(Long resumeId, SkillRequest skillRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Skill skill = skillMapper.toEntity(skillRequest);

        resume.addSkill(skill);

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
        Resume resume = resumeRepository.findById(skill.getResume().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resume.removeSkill(skill);

        skillRepository.delete(skill);
    }

    public SkillResponse setType(Long skillId, Long typeId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        SkillType type = skillTypeRepository.findById(typeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        skill.setType(type);

        return skillMapper.toResponse(skillRepository.save(skill));
    }
}
