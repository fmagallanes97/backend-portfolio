package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.ProjectRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.ProjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Project", description = "Operations about the project entity")
public interface ProjectResource {

    @Operation(description = "Save a new project information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resume not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/resume/{resumeId}/project")
    ResponseEntity<ProjectResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                         @RequestBody @Validated @Parameter(description = "Project information", required = true) ProjectRequest request);

    @Operation(description = "Get information about a project", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Project not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/project/{projectId}")
    ResponseEntity<ProjectResponse> getOne(@PathVariable @Parameter(description = "Project id", required = true) Long projectId);

    @Operation(description = "Get the complete project section about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ProjectResponse.class))
                    )
            )
    })
    @GetMapping("/resume/{resumeId}/project")
    ResponseEntity<List<ProjectResponse>> get(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);

    @Operation(description = "Update the information about a project", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Project not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/project/{projectId}")
    ResponseEntity<ProjectResponse> update(@PathVariable @Parameter(description = "Project id", required = true) Long projectId,
                                           @RequestBody @Validated @Parameter(description = "Project information", required = true) ProjectRequest request);

    @Operation(description = "Delete an existing project", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Project deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Project not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/project/{projectId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Project id", required = true) Long projectId);
}
