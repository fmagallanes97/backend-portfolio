package dev.fmagallanes97.backendportfolio.contact;

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
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Contact updateById(Long id, Contact contact) {
        Contact probableContact = repository.findById(id).orElseThrow(RuntimeException::new);

        probableContact.setEmail(contact.getEmail());
        probableContact.setGithub(contact.getGithub());
        probableContact.setLinkedin(contact.getLinkedin());
        probableContact.setResume(contact.getResume());

        return repository.save(probableContact);
    }

    public void deleteById(Long id) {
        Contact probableContact = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(probableContact);
    }
}
