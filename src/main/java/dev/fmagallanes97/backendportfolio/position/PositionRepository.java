package dev.fmagallanes97.backendportfolio.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    public List<Position> findAllByResumeId(Long id);
}
