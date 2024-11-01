package com.template.spring300_jdk17.exception;

// Base Custom Exception Class
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

