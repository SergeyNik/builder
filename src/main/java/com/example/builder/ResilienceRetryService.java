package com.example.builder;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@Service
public class ResilienceRetryService implements RetryService {

    public void execute() {
        RetryConfig config = RetryConfig.custom().maxAttempts(2).build();
        RetryRegistry registry = RetryRegistry.of(config);
        Retry retry = registry.retry("my");
        Function<Integer, String> decorated = Retry.decorateFunction(retry, this::recover);
        try {
            decorated.apply(3);
        } catch (RuntimeException e) {
            log.error("Error after retry " + e.getMessage());
        }
    }

    private String recover(int i) {
        throw new RuntimeException();
    }
}