package dev.fmagallanes97.backendportfolio.shared.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String title;
    private final String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<InvalidArgumentResponse> arguments;

    public ErrorResponse(String title, String description) {
        this.title = title;
        this.description = description;
        this.arguments = null;
    }
}
