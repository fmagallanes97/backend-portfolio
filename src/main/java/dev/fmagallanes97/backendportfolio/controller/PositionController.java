package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.PositionResource;
import dev.fmagallanes97.backendportfolio.dto.request.PositionRequest;
import dev.fmagallanes97.backendportfolio.dto.response.PositionResponse;
import dev.fmagallanes97.backendportfolio.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PositionController implements PositionResource {

    private final PositionService service;

    public ResponseEntity<PositionResponse> save(Long resumeId, PositionRequest request) {
        PositionResponse response = service.save(resumeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PositionResponse> getOne(Long positionId) {
        PositionResponse response = service.findById(positionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<PositionResponse>> get(Long resumeId) {
        List<PositionResponse> response = service.findAllByResumeId(resumeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PositionResponse> update(Long positionId, PositionRequest request) {
        PositionResponse response = service.updateById(positionId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long positionId) {
        service.deleteById(positionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
