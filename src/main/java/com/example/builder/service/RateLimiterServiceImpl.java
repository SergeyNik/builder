package com.example.builder.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
@Service
public class RateLimiterServiceImpl implements RateLimiterService {

    @Override
    public void execute() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofSeconds(1))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);

        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry
                .rateLimiter("name2", config);

        Supplier<String> hi = RateLimiter.decorateSupplier(rateLimiterWithCustomConfig, () -> "hi");
        log.info("rate-limiter: {}", hi.get());
        log.info("rate-limiter: {}", hi.get());
        log.info("rate-limiter: {}", hi.get());
        log.info("rate-limiter: {}", hi.get());
    }
}
