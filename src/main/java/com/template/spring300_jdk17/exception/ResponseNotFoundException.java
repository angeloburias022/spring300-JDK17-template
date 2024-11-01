package com.template.spring300_jdk17.exception;

public class ResponseNotFoundException extends CustomException {
    public ResponseNotFoundException(String message) {
        super(message);
    }
}