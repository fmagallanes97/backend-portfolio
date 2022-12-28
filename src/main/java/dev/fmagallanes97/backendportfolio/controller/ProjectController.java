package dev.fmagallanes97.backendportfolio.controller;

import dev.fmagallanes97.backendportfolio.controller.resource.ProjectResource;
import dev.fmagallanes97.backendportfolio.dto.request.ProjectRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ProjectResponse;
import dev.fmagallanes97.backendportfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController implements ProjectResource {

    private final ProjectService service;

    public ResponseEntity<ProjectResponse> save(Long resumeId, ProjectRequest request) {
        ProjectResponse response = service.save(resumeId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ProjectResponse> getOne(Long projectId) {
        ProjectResponse response = service.findById(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<ProjectResponse>> get(Long resumeId) {
        List<ProjectResponse> response = service.findAllByResumeId(resumeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ProjectResponse> update(Long projectId, ProjectRequest request) {
        ProjectResponse response = service.updateById(projectId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long projectId) {
        service.deleteById(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
