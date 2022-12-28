package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.SkillTypeResponse;
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

@Tag(name = "Skill type", description = "Operations about the skill type entity")
public interface SkillTypeResource {

    @Operation(description = "Save a new skill type information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Skill type created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillTypeResponse.class)
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
    @PostMapping("/skill_type")
    ResponseEntity<SkillTypeResponse> save(@RequestBody @Validated @Parameter(description = "Skill type information", required = true) SkillTypeRequest request);

    @Operation(description = "Get information about a skill type", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill type retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillTypeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/skill_type/{typeId}")
    ResponseEntity<SkillTypeResponse> getOne(@PathVariable @Parameter(description = "Skill type id", required = true) Long typeId);

    @Operation(description = "Get the complete skill types section", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill type retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SkillTypeResponse.class))
                    )
            )
    })
    @GetMapping("/skill_type")
    ResponseEntity<List<SkillTypeResponse>> get();

    @Operation(description = "Update the information about a skill type", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill type updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillTypeResponse.class)
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
                    description = "Skill type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/skill_type/{typeId}")
    ResponseEntity<SkillTypeResponse> update(@PathVariable @Parameter(description = "Skill type id", required = true) Long typeId,
                                             @RequestBody @Validated @Parameter(description = "Skill type information", required = true) SkillTypeRequest request);

    @Operation(description = "Delete an existing skill type", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill type deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillTypeResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/skill_type/{typeId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Skill type id", required = true) Long typeId);
}
