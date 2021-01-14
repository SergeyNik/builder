package com.example.builder.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.base.Stopwatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimiterServiceImpl implements RateLimiterService {

    private final RateLimiterRegistry rateLimiter;

    @Override
    public void execute() {
        RateLimiter first = rateLimiter.rateLimiter("first");
        Stopwatch stopwatch = Stopwatch.createStarted();
        String str = first.executeSupplier(() -> "finish");
        log.info("result: {}", str);
        stopwatch.stop();
        log.info("time = {}", stopwatch.toString());
    }
}
