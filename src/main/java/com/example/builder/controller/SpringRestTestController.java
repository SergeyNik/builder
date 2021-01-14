package com.example.builder.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpringRestTestController {

    @GetMapping("/test-rest")
    public void callRetryService() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(URI.create("https://www.callicoder.com/")).build();
        String body = restTemplate.exchange(request, String.class).getBody();
        log.info("callicoder body : {}", body);
    }
}
