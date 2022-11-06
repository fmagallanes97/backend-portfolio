package dev.fmagallanes97.backendportfolio.skill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    public List<Skill> findAllByResumeId(Long id);
}
