package com.example.builder.controller;

import com.example.builder.service.integration.Viewer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "The User API")
@RestController
public class SpringIntegrationController {

    @Autowired
    private Viewer viewer;

    @Operation(summary = "Get users",
            description = "Get list of users", tags = "User")
    @GetMapping("/http-integration")
    public void callRetryService() {
        for (int i = 0; i < 10; i++) {
            viewer.send(MessageBuilder.withPayload("google").build());
        }
    }
}
