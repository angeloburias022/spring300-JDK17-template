package com.template.spring300_jdk17.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.template.spring300_jdk17.dto.ApiResponse;
import com.template.spring300_jdk17.util.enums.Status;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
