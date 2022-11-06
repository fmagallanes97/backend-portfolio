package dev.fmagallanes97.backendportfolio.positionresponsibility;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilityService {

    private final ResponsibilityRepository repository;

    public ResponsibilityService(ResponsibilityRepository repository) {
        this.repository = repository;
    }

    public Responsibility save(Responsibility responsibility) {
        return repository.save(responsibility);
    }

    public Responsibility findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Responsibility> findAllByPositionId(Long id) {
        return repository.findAllByPositionId(id);
    }

    public Responsibility updateById(Long id, Responsibility responsibility) {
        Responsibility probableResponsibility = repository.findById(id).orElseThrow(RuntimeException::new);

        probableResponsibility.setDescription(responsibility.getDescription());
        probableResponsibility.setPosition(responsibility.getPosition());

        return repository.save(probableResponsibility);
    }

    public void deleteById(Long id) {
        Responsibility probableResponsibility = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableResponsibility);
    }
}
