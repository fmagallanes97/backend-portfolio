package dev.fmagallanes97.backendportfolio.resume;

import dev.fmagallanes97.backendportfolio.contact.Contact;
import dev.fmagallanes97.backendportfolio.contact.ContactRepository;
import dev.fmagallanes97.backendportfolio.education.Education;
import dev.fmagallanes97.backendportfolio.education.EducationRepository;
import dev.fmagallanes97.backendportfolio.position.Position;
import dev.fmagallanes97.backendportfolio.position.PositionRepository;
import dev.fmagallanes97.backendportfolio.project.Project;
import dev.fmagallanes97.backendportfolio.project.ProjectRepository;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeRequest;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeResponse;
import dev.fmagallanes97.backendportfolio.resume.dto.mapper.ResumeMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.skill.Skill;
import dev.fmagallanes97.backendportfolio.skill.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeMapper resumeMapper;
    private final ResumeRepository resumeRepository;
    private final ContactRepository contactRepository;
    private final EducationRepository educationRepository;
    private final PositionRepository positionRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;

    public ResumeResponse save(ResumeRequest resumeRequest) {
        Resume resume = resumeMapper.toEntity(resumeRequest);

        return resumeMapper.toResponse(resumeRepository.save(resume));
    }

    public ResumeResponse findById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Contact contact = contactRepository.findById(resumeId).orElse(null);
        List<Education> education = educationRepository.findAllByResumeId(resumeId);
        List<Position> positions = positionRepository.findAllByResumeId(resumeId);
        List<Project> projects = projectRepository.findAllByResumeId(resumeId);
        List<Skill> skills = skillRepository.findAllByResumeId(resumeId);

        return resumeMapper.toResponse(
                resume,
                contact,
                education,
                positions,
                projects,
                skills
        );
    }

    public ResumeResponse updateById(Long resumeId, ResumeRequest resumeRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resumeMapper.update(resumeRequest, resume);

        return resumeMapper.toResponse(resumeRepository.save(resume));
    }

    public void deleteById(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resumeRepository.delete(resume);
    }
}
