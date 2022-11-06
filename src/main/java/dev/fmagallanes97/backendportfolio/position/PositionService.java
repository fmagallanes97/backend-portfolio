package dev.fmagallanes97.backendportfolio.position;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository repository;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public Position save(Position position) {
        return repository.save(position);
    }

    public Position findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Position> findAllByResumeId(Long id) {
        return repository.findAllByResumeId(id);
    }

    public Position updateById(Long id, Position position) {
        Position probablePosition = repository.findById(id).orElseThrow(RuntimeException::new);

        probablePosition.setRole(position.getRole());
        probablePosition.setCompanyName(position.getCompanyName());
        probablePosition.setStartDate(position.getStartDate());
        probablePosition.setEndDate(position.getEndDate());
        probablePosition.setResume(position.getResume());

        return repository.save(probablePosition);
    }

    public void deleteById(Long id) {
        Position probablePosition = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probablePosition);
    }
}
