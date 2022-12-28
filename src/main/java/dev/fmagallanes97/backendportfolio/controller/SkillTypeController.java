package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.SkillTypeResource;
import dev.fmagallanes97.backendportfolio.dto.request.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillTypeResponse;
import dev.fmagallanes97.backendportfolio.service.SkillTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillTypeController implements SkillTypeResource {

    private final SkillTypeService service;

    public ResponseEntity<SkillTypeResponse> save(SkillTypeRequest request) {
        SkillTypeResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SkillTypeResponse> getOne(Long typeId) {
        SkillTypeResponse response = service.findById(typeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<SkillTypeResponse>> get() {
        List<SkillTypeResponse> response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SkillTypeResponse> update(Long typeId, SkillTypeRequest request) {
        SkillTypeResponse response = service.updateById(typeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long typeId) {
        service.deleteById(typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
