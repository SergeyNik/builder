package com.example.builder.controller;

import com.example.builder.service.integration.Viewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringIntegrationController {

    @Autowired
    private Viewer viewer;

    @GetMapping("/http-integration")
    public void callRetryService() {
        for (int i = 0; i < 10; i++) {
            viewer.send(MessageBuilder.withPayload("google").build());
        }
    }
}
