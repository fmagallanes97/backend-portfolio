package dev.fmagallanes97.backendportfolio.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ErrorResponse(
        String title,
        String description,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<InvalidArgumentResponse> arguments
) {
    public ErrorResponse(String title, String description) {
        this(title, description, null);
    }
}
