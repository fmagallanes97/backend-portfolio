package dev.fmagallanes97.backendportfolio.contact;

import dev.fmagallanes97.backendportfolio.contact.dto.ContactRequest;
import dev.fmagallanes97.backendportfolio.contact.dto.ContactResponse;
import dev.fmagallanes97.backendportfolio.contact.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.ResumeRepository;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;
    private final ResumeRepository resumeRepository;
    private final ContactRepository contactRepository;

    public ContactResponse save(Long resumeId, ContactRequest contactRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Contact contact = contactMapper.toEntity(contactRequest);

        resume.addContact(contact);

        return contactMapper.toResponse(contactRepository.save(contact));
    }

    public ContactResponse findById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        return contactMapper.toResponse(contact);
    }

    public ContactResponse updateById(Long contactId, ContactRequest contactRequest) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        contactMapper.update(contactRequest, contact);

        return contactMapper.toResponse(contactRepository.save(contact));
    }

    public void deleteById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });
        Resume resume = resumeRepository.findById(contact.getResume().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException(Error.RESOURCE_NOT_FOUND);
        });

        resume.removeContact(contact);

        contactRepository.delete(contact);
    }
}
