package com.template.spring300_jdk17.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.template.spring300_jdk17.dto.ApiResponse;
import com.template.spring300_jdk17.util.enums.Status;

@RestControllerAdvice
public class GlobalExceptionHandler {

     /**
     * Handles MethodArgumentNotValidException for validation errors and returns a
     * 400 Bad Request status.
     *
     * @param ex the MethodArgumentNotValidException thrown when method arguments
     *           fail validation
     * @return ResponseEntity containing an ApiResponse with error details and an
     *         HTTP status code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleArgumentMethod(MethodArgumentNotValidException ex) {
        // Create the error details
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Create the ApiResponse object
        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setStatus(Status.ERROR);
        response.setMessage("Validation failed for one or more arguments.");
        response.setData(errors);
        response.setErrors(List.of(errors));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(Status.ERROR);
        response.setMessage(ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResponseNotFoundException(ResponseNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(Status.ERROR);
        response.setMessage(ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(Status.ERROR);
        response.setMessage(ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(Status.ERROR);
        response.setMessage("An unexpected error occurred: " + ex.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
