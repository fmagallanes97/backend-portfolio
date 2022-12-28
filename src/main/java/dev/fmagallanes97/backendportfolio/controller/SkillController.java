package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.SkillResource;
import dev.fmagallanes97.backendportfolio.dto.request.SkillRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillResponse;
import dev.fmagallanes97.backendportfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SkillController implements SkillResource {

    private final SkillService service;

    public ResponseEntity<SkillResponse> save(Long resumeId, SkillRequest request) {
        SkillResponse response = service.save(resumeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SkillResponse> getOne(Long skillId) {
        SkillResponse response = service.findById(skillId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<SkillResponse>> get(Long resumeId) {
        List<SkillResponse> response = service.findAllByResumeId(resumeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SkillResponse> update(Long skillId, SkillRequest request) {
        SkillResponse response = service.updateById(skillId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long skillId) {
        service.deleteById(skillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<SkillResponse> setType(Long typeId, Long skillId) {
        SkillResponse response = service.setType(skillId, typeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
