package com.example.builder.controller;

import com.example.builder.service.RateLimiterService;
import com.example.builder.service.retry.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class ResilienceController {

    private final RetryService resilienceRetryService;
    private final RateLimiterService rateLimiterService;

    @GetMapping("/resilience-retry")
    public void callRetryService() throws SQLException {
        resilienceRetryService.execute();
    }

    @GetMapping("/rate-limiter")
    public void callRateLimiterService() throws SQLException {
        rateLimiterService.execute();
    }
}
