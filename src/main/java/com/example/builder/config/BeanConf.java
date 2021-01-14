package com.example.builder.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BeanConf {

    @Bean
    public RateLimiterRegistry rateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(5))
                .limitForPeriod(1)
                .build();

        return RateLimiterRegistry.of(config);
    }
}
