package dev.fmagallanes97.backendportfolio.service;

import dev.fmagallanes97.backendportfolio.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.dto.request.ContactRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ContactResponse;
import dev.fmagallanes97.backendportfolio.exception.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.model.Contact;
import dev.fmagallanes97.backendportfolio.model.Resume;
import dev.fmagallanes97.backendportfolio.repository.ContactRepository;
import dev.fmagallanes97.backendportfolio.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.CONTACT;
import static dev.fmagallanes97.backendportfolio.exception.ErrorResource.RESUME;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;
    private final ResumeRepository resumeRepository;
    private final ContactRepository contactRepository;

    public ContactResponse save(Long resumeId, ContactRequest contactRequest) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));
        Contact contact = contactMapper.toEntity(contactRequest);

        resume.addContact(contact);

        return contactMapper.toResponse(contactRepository.save(contact));
    }

    public ContactResponse findById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException(CONTACT.getName(), contactId));
        return contactMapper.toResponse(contact);
    }

    public ContactResponse updateById(Long contactId, ContactRequest contactRequest) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException(CONTACT.getName(), contactId));

        contactMapper.update(contactRequest, contact);

        return contactMapper.toResponse(contactRepository.save(contact));
    }

    public void deleteById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException(CONTACT.getName(), contactId));
        Long resumeId = contact.getResume().getId();
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new ResourceNotFoundException(RESUME.getName(), resumeId));

        resume.removeContact(contact);

        contactRepository.delete(contact);
    }
}
