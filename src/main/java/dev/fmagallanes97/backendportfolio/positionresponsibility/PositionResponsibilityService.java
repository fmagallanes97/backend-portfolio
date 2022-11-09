package dev.fmagallanes97.backendportfolio.positionresponsibility;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionResponsibilityService {

    private final PositionResponsibilityRepository repository;

    public PositionResponsibilityService(PositionResponsibilityRepository repository) {
        this.repository = repository;
    }

    public PositionResponsibility save(PositionResponsibility responsibility) {
        return repository.save(responsibility);
    }

    public PositionResponsibility findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<PositionResponsibility> findAllByPositionId(Long id) {
        return repository.findAllByPositionId(id);
    }

    public PositionResponsibility updateById(Long id, PositionResponsibility responsibility) {
        PositionResponsibility probableResponsibility = repository.findById(id).orElseThrow(RuntimeException::new);

        probableResponsibility.setDescription(responsibility.getDescription());
        probableResponsibility.setPosition(responsibility.getPosition());

        return repository.save(probableResponsibility);
    }

    public void deleteById(Long id) {
        PositionResponsibility probableResponsibility = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableResponsibility);
    }
}
