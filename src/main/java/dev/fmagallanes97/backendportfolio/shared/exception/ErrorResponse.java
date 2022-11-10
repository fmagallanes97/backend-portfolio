package dev.fmagallanes97.backendportfolio.shared.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> reasons;
}
