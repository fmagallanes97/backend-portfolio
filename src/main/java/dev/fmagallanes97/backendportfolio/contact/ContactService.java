package dev.fmagallanes97.backendportfolio.contact;

import dev.fmagallanes97.backendportfolio.contact.dto.ContactRequest;
import dev.fmagallanes97.backendportfolio.contact.dto.ContactResponse;
import dev.fmagallanes97.backendportfolio.contact.dto.mapper.ContactMapper;
import dev.fmagallanes97.backendportfolio.shared.exception.Error;
import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactResponse save(ContactRequest contactRequest) {
        Contact contact = contactMapper.toEntity(contactRequest);

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

        contactRepository.delete(contact);
    }
}
