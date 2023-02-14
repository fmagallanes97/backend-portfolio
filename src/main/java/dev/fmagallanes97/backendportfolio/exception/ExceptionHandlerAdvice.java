package dev.fmagallanes97.backendportfolio.exception;

import dev.fmagallanes97.backendportfolio.dto.response.InvalidArgumentResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handle(NoHandlerFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                "Testing..."
        );

        problemDetail.setTitle("Not found");
        problemDetail.setType(URI.create("http://localhost:8080/error/not-found"));

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleUnknownResource(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        String resourceName = StringUtils.capitalize(ex.getResourceName());

        problemDetail.setTitle(resourceName + " Not found");
        problemDetail.setType(URI.create("http://localhost:8080/error/resource-not-found"));
        problemDetail.setProperty("id", ex.getResourceId());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<InvalidArgumentResponse> arguments = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new InvalidArgumentResponse(error.getField(), error.getDefaultMessage())).toList();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "The request body contains invalid or missing parameters that do not meet the required constraints. Please check the documentation for the correct parameter"
        );

        problemDetail.setTitle("Invalid Request Body");
        problemDetail.setType(URI.create("http://localhost:8080/error/bad-request"));
        problemDetail.setProperty("errors", arguments);

        return problemDetail;
    }
}
