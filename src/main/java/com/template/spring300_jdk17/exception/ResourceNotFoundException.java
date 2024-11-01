package com.template.spring300_jdk17.exception;

// Specific Exceptions Extending CustomException
public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}