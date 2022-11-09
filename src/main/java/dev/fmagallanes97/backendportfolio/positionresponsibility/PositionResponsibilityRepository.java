package dev.fmagallanes97.backendportfolio.positionresponsibility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionResponsibilityRepository extends JpaRepository<PositionResponsibility, Long> {
    public List<PositionResponsibility> findAllByPositionId(Long id);
}
