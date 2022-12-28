package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.ContactResource;
import dev.fmagallanes97.backendportfolio.dto.request.ContactRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ContactResponse;
import dev.fmagallanes97.backendportfolio.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController implements ContactResource {

    private final ContactService service;

    public ResponseEntity<ContactResponse> save(Long resumeId, ContactRequest contactRequest) {
        ContactResponse response = service.save(resumeId, contactRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ContactResponse> getOne(Long contactId) {
        ContactResponse response = service.findById(contactId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ContactResponse> update(Long contactId, ContactRequest contactRequest) {
        ContactResponse response = service.updateById(contactId, contactRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long contactId) {
        service.deleteById(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
