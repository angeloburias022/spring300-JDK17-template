package com.template.spring300_jdk17.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.spring300_jdk17.dto.ApiResponse;
import com.template.spring300_jdk17.exception.CustomException;
import com.template.spring300_jdk17.exception.ResourceNotFoundException;
@RestController
@RequestMapping("/exception")
public class ExceptionController {
     @GetMapping("resource-not-found")
     public ResponseEntity<ApiResponse<String>> throwError() {
         // Intentionally throw a custom exception to test the global exception handler
         throw new ResourceNotFoundException("Resource not found.");
     }
    
    @GetMapping("custom-error")
    public ResponseEntity<ApiResponse<String>> throwCustomError() {
        // Intentionally throw a custom exception to return a specific error response
        throw new CustomException("This is a custom error.");
    }
}
