package dev.fmagallanes97.backendportfolio.controller.resource;

import dev.fmagallanes97.backendportfolio.dto.request.ContactRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ContactResponse;
import dev.fmagallanes97.backendportfolio.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Contact", description = "Operations about the contact entity")
public interface ContactResource {

    @Operation(description = "Save a new contact information", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Contact created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactResponse.class)
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
    @PostMapping("/resume/{resumeId}/contact")
    ResponseEntity<ContactResponse> save(@PathVariable @Parameter(description = "Resume id", required = true) Long resumeId,
                                         @RequestBody @Validated @Parameter(description = "Contact information", required = true) ContactRequest request);

    @Operation(description = "Get information about a contact", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contact retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contact not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/contact/{contactId}")
    ResponseEntity<ContactResponse> getOne(@PathVariable @Parameter(description = "Contact id", required = true) Long contactId);

    @Operation(description = "Update the information about a contact", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contact updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactResponse.class)
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
                    description = "Contact not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @PutMapping("/contact/{contactId}")
    ResponseEntity<ContactResponse> update(@PathVariable @Parameter(description = "Contact id", required = true) Long contactId,
                                           @RequestBody @Validated @Parameter(description = "Contact information", required = true) ContactRequest request);

    @Operation(description = "Delete an existing contact", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contact deleted successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contact not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/contact/{contactId}")
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Contact id", required = true) Long contactId);
}
