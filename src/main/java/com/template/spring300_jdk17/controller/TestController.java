package com.template.spring300_jdk17.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.template.spring300_jdk17.dto.ApiResponse;
import com.template.spring300_jdk17.dto.UserDto;
import com.template.spring300_jdk17.util.enums.Status;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

    private Map<Integer, String> dataStore = new HashMap<>(); // A simple in-memory data store using a map.

    /**
     * Handles POST requests to create a new resource.
     *
     * @param id    the ID of the new resource
     * @param value the value of the new resource
     * @return ResponseEntity containing a success message or a conflict error
     * 
     *         Example curl command:
     *         curl -X POST http://localhost:8080/api/items -d
     *         "id=1&value=SampleItem"
     */
    @PostMapping("/items") // Maps HTTP POST requests to /api/items to this method.
    public ResponseEntity<String> createItem(@Valid @RequestBody UserDto value) {
        // Checks if the item already exists in the data store.
        if (dataStore.containsKey(value.getId())) {
            // Returns a 409 Conflict status if the item already exists.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item already exists");
        }
        // Adds the new item to the data store.
        dataStore.put(value.getId(), value.getFirstName());
        // Returns a 201 Created status with a success message.
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
    }

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
