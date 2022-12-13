package dev.fmagallanes97.backendportfolio.repository;

import dev.fmagallanes97.backendportfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    public List<Skill> findAllByResumeId(Long id);
}
