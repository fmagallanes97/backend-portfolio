package dev.fmagallanes97.backendportfolio.skilltype;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillTypeService {

    private final SkillTypeRepository repository;

    public SkillTypeService(SkillTypeRepository repository) {
        this.repository = repository;
    }

    public SkillType save(SkillType skillType) {
        return repository.save(skillType);
    }

    public SkillType findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
    }

    public List<SkillType> findAll() {
        return repository.findAll();
    }

    public SkillType updateById(Long id, SkillType skillType) {
        SkillType probableSkillType = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        probableSkillType.setName(skillType.getName());

        return repository.save(probableSkillType);
    }

    public void deleteById(Long id) {
        SkillType probableSkillType = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        repository.delete(probableSkillType);
    }
}
