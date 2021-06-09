package com.example.builder.controller;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpringRestTestController {

    @GetMapping("/test-rest")
    public void callRetryService() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(URI.create("https://www.callicoder.com/")).build();
        String body = restTemplate.exchange(request, String.class).getBody();
        stopwatch.stop();
        log.info("callicoder body : {}, time = {}", body, stopwatch);
    }

    @GetMapping("/test-exception")
    public void callExp() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(URI.create("https://www.notexists.com/")).build();
        try {
            String body = restTemplate.exchange(request, String.class).getBody();
            log.info("{}", body);
        } catch (RestClientException e) {
            if (e instanceof HttpStatusCodeException) {
                log.error("{}", ((HttpStatusCodeException) e).getStatusCode().value());
            }
        }
    }
}
