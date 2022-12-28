package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.PositionRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.PositionResponse;
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

@Tag(name = "Position", description = "Operations about the job position entity")
public interface PositionResource {
    @Operation(description = "Save a new job position information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Position created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositionResponse.class)
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
    @PostMapping("/resume/{resumeId}/position")
    ResponseEntity<PositionResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                          @RequestBody @Validated @Parameter(description = "Position information", required = true) PositionRequest request);

    @Operation(description = "Get information about a job position", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositionResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Position not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/position/{positionId}")
    ResponseEntity<PositionResponse> getOne(@PathVariable @Parameter(description = "Position id", required = true) Long positionId);

    @Operation(description = "Get the complete job section about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PositionResponse.class))
                    )
            )
    })
    @GetMapping("/resume/{resumeId}/position")
    ResponseEntity<List<PositionResponse>> get(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);

    @Operation(description = "Update the information about a job position", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositionResponse.class)
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
                    description = "Position not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/position/{positionId}")
    ResponseEntity<PositionResponse> update(@PathVariable @Parameter(description = "Position id", required = true) Long positionId,
                                            @RequestBody @Validated @Parameter(description = "Position information", required = true) PositionRequest request);

    @Operation(description = "Delete an existing job position", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositionResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Position not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/position/{positionId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Position id", required = true) Long positionId);
}
