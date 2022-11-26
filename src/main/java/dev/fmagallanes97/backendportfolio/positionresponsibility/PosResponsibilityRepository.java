package dev.fmagallanes97.backendportfolio.positionresponsibility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosResponsibilityRepository extends JpaRepository<PosResponsibility, Long> {
    public List<PosResponsibility> findAllByPositionId(Long id);
}
