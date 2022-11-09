package dev.fmagallanes97.backendportfolio.skilltype;

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
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<SkillType> findAll() {
        return repository.findAll();
    }

    public SkillType updateById(Long id, SkillType skillType) {
        SkillType probableSkillType = repository.findById(id).orElseThrow(RuntimeException::new);

        probableSkillType.setName(skillType.getName());

        return repository.save(probableSkillType);
    }

    public void deleteById(Long id) {
        SkillType probableSkillType = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableSkillType);
    }
}
