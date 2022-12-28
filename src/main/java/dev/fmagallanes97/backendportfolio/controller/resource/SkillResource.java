package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.SkillRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import dev.fmagallanes97.backendportfolio.dto.response.SkillResponse;
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

@Tag(name = "Skill", description = "Operations about the skill entity")
public interface SkillResource {

    @Operation(description = "Save a new skill information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Skill created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillResponse.class)
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
    @PostMapping("/resume/{resumeId}/skill")
    ResponseEntity<SkillResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                       @RequestBody @Validated @Parameter(description = "Skill information", required = true) SkillRequest request);

    @Operation(description = "Get information about a skill", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/skill/{skillId}")
    ResponseEntity<SkillResponse> getOne(@PathVariable @Parameter(description = "Skill id", required = true) Long skillId);

    @Operation(description = "Get the complete skill section about a resume", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = SkillResponse.class))
                    )
            )
    })
    @GetMapping("/resume/{resumeId}/skill")
    ResponseEntity<List<SkillResponse>> get(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId);

    @Operation(description = "Update the information about a skill", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillResponse.class)
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
                    description = "Skill not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/skill/{skillId}")
    ResponseEntity<SkillResponse> update(@PathVariable @Parameter(description = "Skill id", required = true) Long skillId,
                                         @RequestBody @Validated @Parameter(description = "Skill information", required = true) SkillRequest request);

    @Operation(description = "Delete an existing skill", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/skill/{skillId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Skill id", required = true) Long skillId);

    @Operation(description = "Set the type of the skill", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Type set successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SkillResponse.class)
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
                    description = "Resource not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("skill/{skillId}/skill_type/{typeId}")
    ResponseEntity<SkillResponse> setType(@PathVariable @Parameter(description = "Skill id", required = true) Long skillId,
                                          @PathVariable @Parameter(description = "Type id", required = true) Long typeId);
}
