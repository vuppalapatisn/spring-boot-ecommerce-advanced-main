package com.example.demo.controller;

import com.example.demo.service.ComputeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final ComputeService service;

    public HealthController(ComputeService service) {
        this.service = service;
    }

    @GetMapping("/compute")
    public long compute() {
        return service.compute();
    }
}
