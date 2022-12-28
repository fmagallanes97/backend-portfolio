package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.PosResponsibilityResponse;
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

@Tag(name = "Position responsibility", description = "Operations about the position responsibility entity")
public interface PosResponsibilityResource {
    @Operation(description = "Save a new position responsibility information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Position responsibility created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PosResponsibilityResponse.class)
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
                    description = "Job position not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/position/{positionId}/position_responsibility")
    ResponseEntity<PosResponsibilityResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long positionId,
                                                   @RequestBody @Validated @Parameter(description = "Position responsibility information", required = true) PosResponsibilityRequest request);

    @Operation(description = "Get information about a position responsibility", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position responsibility retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PosResponsibilityResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Position responsibility not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/position_responsibility/{responsibilityId}")
    ResponseEntity<PosResponsibilityResponse> getOne(@PathVariable @Parameter(description = "Position responsibility id", required = true) Long responsibilityId);

    @Operation(description = "Get the complete responsibility section about a job", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position responsibility retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PosResponsibilityResponse.class))
                    )
            )
    })
    @GetMapping("/position/{positionId}/position_responsibility")
    ResponseEntity<List<PosResponsibilityResponse>> get(@PathVariable @Parameter(description = "Resume id", required = true) Long positionId);

    @Operation(description = "Update the information about a position responsibility", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position responsibility updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PosResponsibilityResponse.class)
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
                    description = "Job position not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/position_responsibility/{responsibilityId}")
    ResponseEntity<PosResponsibilityResponse> update(@PathVariable @Parameter(description = "Position responsibility id", required = true) Long responsibilityId,
                                                     @RequestBody @Validated @Parameter(description = "Position responsibility information", required = true) PosResponsibilityRequest request);

    @Operation(description = "Delete an existing position responsibility", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Position responsibility deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PosResponsibilityResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Position responsibility not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/position_responsibility/{responsibilityId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Position responsibility id", required = true) Long responsibilityId);
}
