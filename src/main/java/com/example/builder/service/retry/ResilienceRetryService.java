package com.example.builder.service.retry;

import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResilienceRetryService implements RetryService {

    private final RetryRegistry retryRegistry;
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Retry(name = "backendA", fallbackMethod = "recover")
    @Override
    public void execute() {
        throw new RuntimeException();
    }

    @Override
    public void executeRetryRegistry() {
        io.github.resilience4j.retry.Retry retry = retryRegistry.retry("retry");
        log.info("retryGet {}" , retry.getName());
        try {
            retry.executeRunnable(() -> {
                log.info("retryExecute {}", atomicInteger.incrementAndGet());
                throw new RuntimeException();
            });
        } catch (RuntimeException e) {
            log.error("retry {}", e.getMessage());
        }
    }

    private void recover(RuntimeException ex) {
        log.error("Error after retry ");
    }
}