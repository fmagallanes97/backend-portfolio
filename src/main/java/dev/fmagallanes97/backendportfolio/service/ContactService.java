package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.repository.ContactRepository;
import dev.fmagallanes97.backendportfolio.dto.request.ContactRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ContactResponse;
import dev.fmagallanes97.backendportfolio.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.model.Contact;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import dev.fmagallanes97.backendportfolio.exception.Error;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
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
