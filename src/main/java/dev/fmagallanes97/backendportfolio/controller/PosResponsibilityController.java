package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.PosResponsibilityResource;
import dev.fmagallanes97.backendportfolio.dto.request.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.dto.response.PosResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.service.PosResponsibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PosResponsibilityController implements PosResponsibilityResource {

    private final PosResponsibilityService service;

    public ResponseEntity<PosResponsibilityResponse> save(Long positionId, PosResponsibilityRequest request) {
        PosResponsibilityResponse response = service.save(positionId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PosResponsibilityResponse> getOne(Long responsibilityId) {
        PosResponsibilityResponse response = service.findById(responsibilityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<PosResponsibilityResponse>> get(Long positionId) {
        List<PosResponsibilityResponse> response = service.findAllByPositionId(positionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<PosResponsibilityResponse> update(Long responsibilityId, PosResponsibilityRequest request) {
        PosResponsibilityResponse response = service.updateById(responsibilityId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long responsibilityId) {
        service.deleteById(responsibilityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
