package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.ResumeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.ResumeResponse;
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

@Tag(name = "Resume", description = "Operations about the resume entity")
public interface ResumeResource {

    @Operation(description = "Save a new resume information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Resume created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResumeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/resume")
    ResponseEntity<ResumeResponse> save(@RequestBody @Validated @Parameter(description = "Resume information", required = true) ResumeRequest request);

    @Operation(description = "Get information about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Resume retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResumeResponse.class)
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
    @GetMapping("/resume/{resumeId}")
    ResponseEntity<ResumeResponse> getOne(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);

    @Operation(description = "Update the information about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Resume updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResumeResponse.class)
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
    @PutMapping("/resume/{resumeId}")
    ResponseEntity<ResumeResponse> update(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                          @RequestBody @Validated @Parameter(description = "Resume information", required = true) ResumeRequest request);

    @Operation(description = "Delete an existing resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Resume deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResumeResponse.class)
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
    @DeleteMapping("/resume/{resumeId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);
}
