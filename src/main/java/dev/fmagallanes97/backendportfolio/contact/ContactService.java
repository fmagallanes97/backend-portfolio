package dev.fmagallanes97.backendportfolio.contact;

import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public Contact findByResumeId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
    }

    public Contact updateById(Long id, Contact contact) {
        Contact probableContact = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        probableContact.setEmail(contact.getEmail());
        probableContact.setGithubProfileURL(contact.getGithubProfileURL());
        probableContact.setLinkedinProfileURL(contact.getLinkedinProfileURL());
        probableContact.setResume(contact.getResume());

        return repository.save(probableContact);
    }

    public void deleteById(Long id) {
        Contact probableContact = repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        repository.delete(probableContact);
    }
}
