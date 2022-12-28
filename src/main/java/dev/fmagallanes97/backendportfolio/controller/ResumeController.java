package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.ResumeResource;
import dev.fmagallanes97.backendportfolio.dto.request.ResumeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ResumeResponse;
import dev.fmagallanes97.backendportfolio.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResumeController implements ResumeResource {

    private final ResumeService service;

    public ResponseEntity<ResumeResponse> save(ResumeRequest request) {
        ResumeResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResumeResponse> getOne(Long resumeId) {
        ResumeResponse response = service.findById(resumeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResumeResponse> update(Long resumeId, ResumeRequest request) {
        ResumeResponse response = service.updateById(resumeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long resumeId) {
        service.deleteById(resumeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
