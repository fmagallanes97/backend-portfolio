package dev.fmagallanes97.backendportfolio.repository;

import dev.fmagallanes97.backendportfolio.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
