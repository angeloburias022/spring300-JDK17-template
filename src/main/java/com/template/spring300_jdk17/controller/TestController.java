package com.template.spring300_jdk17.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.spring300_jdk17.dto.ApiResponse;
import com.template.spring300_jdk17.util.enums.Status;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("path")
    public ResponseEntity<ApiResponse<String>> getMethodName(@RequestParam String param) {
        ApiResponse<String> response = new ApiResponse<>();

        // Example logic for response based on the request parameter
        if (param != null && !param.isEmpty()) {
            response.setData("Received parameter: " + param);
            response.setStatus(Status.SUCCESS); // Assuming you have a status field
            response.setMessage("Request processed successfully.");
        } else {
            response.setStatus(Status.ERROR);
            response.setMessage("Parameter 'param' is required.");
        }

        return ResponseEntity.ok(response); // Return the response wrapped in a ResponseEntity
    }
}
