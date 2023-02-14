package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.SkillMapper;
import dev.fmagallanes97.backendportfolio.dto.request.SkillRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.model.Skill;
import dev.fmagallanes97.backendportfolio.model.SkillType;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import dev.fmagallanes97.backendportfolio.repository.SkillRepository;
import dev.fmagallanes97.backendportfolio.repository.SkillTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.*;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillMapper skillMapper;
    private final ResumeRepository resumeRepository;
    private final SkillTypeRepository skillTypeRepository;
    private final SkillRepository skillRepository;

    public SkillResponse save(Long resumeId, SkillRequest skillRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        Skill skill = skillMapper.toEntity(skillRequest);

        resume.addSkill(skill);

        return skillMapper.toResponse(skillRepository.save(skill));
    }

    public SkillResponse findById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new ResourceNotFoundException(SKILL.getName(), skillId));
        return skillMapper.toResponse(skill);
    }

    public List<SkillResponse> findAllByResumeId(Long resumeId) {
        return skillMapper.toResponseList(skillRepository.findAllByResumeId(resumeId));
    }

    public SkillResponse updateById(Long skillId, SkillRequest skillRequest) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new ResourceNotFoundException(SKILL.getName(), skillId));

        skillMapper.update(skillRequest, skill);

        return skillMapper.toResponse(skillRepository.save(skill));
    }

    public void deleteById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new ResourceNotFoundException(SKILL.getName(), skillId));
        Long resumeId = skill.getResume().getId();
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));

        resume.removeSkill(skill);

        skillRepository.delete(skill);
    }

    public SkillResponse setType(Long skillId, Long typeId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new ResourceNotFoundException(SKILL.getName(), skillId));
        SkillType type = skillTypeRepository.findById(typeId).orElseThrow(() -> new ResourceNotFoundException(SKILL_TYPE.getName(), typeId));

        skill.setType(type);

        return skillMapper.toResponse(skillRepository.save(skill));
    }
}
