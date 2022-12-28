package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.EducationRequest;
import dev.fmagallanes97.backendportfolio.dto.response.EducationResponse;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
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

@Tag(name = "Education", description = "Operations about the education entity")
public interface EducationResource {

    @Operation(description = "Save a new education information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Education created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EducationResponse.class)
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
    @PostMapping("/resume/{resumeId}/education")
    ResponseEntity<EducationResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                           @RequestBody @Validated @Parameter(description = "Education information", required = true) EducationRequest request);

    @Operation(description = "Get information about a education", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Education retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EducationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Education not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/education/{educationId}")
    ResponseEntity<EducationResponse> getOne(@PathVariable @Parameter(description = "Education id", required = true) Long educationId);

    @Operation(description = "Get the complete education section about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Education retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = EducationResponse.class))
                    )
            )
    })
    @GetMapping("/resume/{resumeId}/education")
    ResponseEntity<List<EducationResponse>> get(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);

    @Operation(description = "Update the information about a education", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Education updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EducationResponse.class)
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
                    description = "Education not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/education/{educationId}")
    ResponseEntity<EducationResponse> update(@PathVariable @Parameter(description = "Education id", required = true) Long educationId,
                                             @RequestBody @Validated @Parameter(description = "Education information", required = true) EducationRequest request);

    @Operation(description = "Delete an existing education", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Education deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EducationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Education not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/education/{educationId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Education id", required = true) Long educationId);
}
