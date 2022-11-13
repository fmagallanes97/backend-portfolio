package dev.fmagallanes97.backendportfolio.shared.exception;

import dev.fmagallanes97.backendportfolio.shared.exception.custom.ResourceNotFoundException;
import dev.fmagallanes97.backendportfolio.shared.exception.dto.ErrorResponse;
import dev.fmagallanes97.backendportfolio.shared.exception.dto.InvalidArgumentResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(ex.getTitle(), ex.getDescription()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<InvalidArgumentResponse> arguments = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new InvalidArgumentResponse(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        return ResponseEntity
                .status(Error.VALIDATION_ERROR.getStatus())
                .body(new ErrorResponse(Error.VALIDATION_ERROR.getTitle(), Error.VALIDATION_ERROR.getDescription(), arguments));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(Error.INVALID_JSON.getStatus())
                .body(new ErrorResponse(Error.INVALID_JSON.getTitle(), Error.INVALID_JSON.getDescription()));
    }
}
