package dev.fmagallanes97.backendportfolio.shared.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public CustomException(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }

    public CustomException(HttpStatus status, String description, List<String> reasons) {
        this(status, description);
        this.reasons = reasons;
    }
}
