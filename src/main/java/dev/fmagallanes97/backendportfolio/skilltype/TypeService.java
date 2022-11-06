package dev.fmagallanes97.backendportfolio.skilltype;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository repository;

    public TypeService(TypeRepository repository) {
        this.repository = repository;
    }

    public Type save(Type type) {
        return repository.save(type);
    }

    public Type findById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Type> findAll() {
        return repository.findAll();
    }

    public Type updateById(Long id, Type type) {
        Type probableType = repository.findById(id).orElseThrow(RuntimeException::new);

        probableType.setName(type.getName());

        return repository.save(probableType);
    }

    public void deleteById(Long id) {
        Type probableType = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableType);
    }
}
