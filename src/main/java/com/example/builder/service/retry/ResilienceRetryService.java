package com.example.builder.service.retry;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResilienceRetryService implements RetryService {

    @Retry(name = "backendA", fallbackMethod = "recover")
    @Override
    public void execute() {
        throw new RuntimeException();
    }

    private void recover(RuntimeException ex) {
        log.error("Error after retry ");
    }
}