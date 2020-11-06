package com.example.builder.controller;

import com.example.builder.service.retry.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class ResilienceRetryController {

    private final RetryService resilienceRetryService;

    @GetMapping("/resilience")
    public void callRetryService() throws SQLException {
        resilienceRetryService.execute();
    }
}
