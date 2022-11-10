package dev.fmagallanes97.backendportfolio.shared.exception;

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
}
