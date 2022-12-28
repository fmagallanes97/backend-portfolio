package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.EducationResource;
import dev.fmagallanes97.backendportfolio.dto.request.EducationRequest;
import dev.fmagallanes97.backendportfolio.dto.response.EducationResponse;
import dev.fmagallanes97.backendportfolio.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationController implements EducationResource {

    private final EducationService service;

    public ResponseEntity<EducationResponse> save(Long resumeId, EducationRequest request) {
        EducationResponse response = service.save(resumeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<EducationResponse> getOne(Long educationId) {
        EducationResponse response = service.findById(educationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<EducationResponse>> get(Long resumeId) {
        List<EducationResponse> response = service.findAllByResumeId(resumeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<EducationResponse> update(Long educationId, EducationRequest request) {
        EducationResponse response = service.updateById(educationId, request);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long educationId) {
        service.deleteById(educationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
