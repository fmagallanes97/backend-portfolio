package dev.fmagallanes97.backendportfolio.positionresponsibility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long> {
    public List<Responsibility> findAllByPositionId(Long id);
}
