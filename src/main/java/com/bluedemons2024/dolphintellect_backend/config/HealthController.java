package com.bluedemons2024.dolphintellect_backend.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {


    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("API HEALTHY!", HttpStatus.OK);
    }
}
