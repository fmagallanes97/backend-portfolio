package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.model.Skill;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import dev.fmagallanes97.backendportfolio.exception.Error;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.repository.SkillRepository;
import dev.fmagallanes97.backendportfolio.dto.request.SkillRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillResponse;
import dev.fmagallanes97.backendportfolio.dto.mapper.SkillMapper;
import dev.fmagallanes97.backendportfolio.model.SkillType;
import dev.fmagallanes97.backendportfolio.repository.SkillTypeRepository;
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
