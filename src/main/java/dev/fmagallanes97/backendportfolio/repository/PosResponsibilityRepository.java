package dev.fmagallanes97.backendportfolio.repository;

import dev.fmagallanes97.backendportfolio.model.PosResponsibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosResponsibilityRepository extends JpaRepository<PosResponsibility, Long> {
    public List<PosResponsibility> findAllByPositionId(Long id);
}
