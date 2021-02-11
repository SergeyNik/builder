package com.example.builder.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimiterServiceImpl implements RateLimiterService {

    private final RateLimiterRegistry rateLimiter;
    private final HttpClient httpClient;

    @SneakyThrows
    @Override
    public void execute() {
        RateLimiter first = rateLimiter.rateLimiter("first");
        HttpGet request = new HttpGet("https://httpstat.us/503/");
        HttpResponse httpResponse = first.executeCheckedSupplier(() -> httpClient.execute(request));
        log.info("{}", httpResponse.getStatusLine().getStatusCode());
    }
}
