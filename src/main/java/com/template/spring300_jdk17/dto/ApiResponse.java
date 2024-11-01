package com.template.spring300_jdk17.dto;

import java.util.List;

import com.template.spring300_jdk17.util.enums.Status;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Status status; 
    private boolean success;
    private String message;
    private T data;
    private List<T> errors;
    private int errorCode;
    private long timestamp;
    private String path;
}
