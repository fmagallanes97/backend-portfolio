package dev.fmagallanes97.backendportfolio.exception;

import dev.fmagallanes97.backendportfolio.dto.response.InvalidArgumentResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "The resource you try to access does not exist. Please check the documentation for the correct URI"
        );

        problemDetail.setTitle("Resource Not Exist");
        problemDetail.setInstance(URI.create(ex.getRequestURL()));
        problemDetail.setType(URI.create("https://example.com/error/resource-not-exist"));

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        String resourceName = StringUtils.capitalize(ex.getResourceName());

        problemDetail.setTitle(resourceName + " Not found");
        problemDetail.setType(URI.create("https://example.com/error/resource-not-found"));
        problemDetail.setProperty("id", ex.getResourceId());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "The request parameter is invalid. Please check the documentation for the correct parameter value"
        );

        problemDetail.setTitle("Invalid Request Parameter");
        problemDetail.setType(URI.create("https://example.com/error/invalid-parameter"));

        return problemDetail;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.METHOD_NOT_ALLOWED,
                "The HTTP method used to access the resource is not allowed. Please check the documentation for the correct HTTP method to use"
        );

        problemDetail.setTitle("Method Not Allowed");
        problemDetail.setType(URI.create("https://example.com/error/method-not-allowed"));

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
        problemDetail.setType(URI.create("https://example.com/error/validation-error"));
        problemDetail.setProperty("errors", arguments);

        return problemDetail;
    }
}
