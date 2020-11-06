package com.example.builder.controller;

import com.example.builder.service.retry.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class SpringRetryController {

    private final RetryService springRetryService;

    @GetMapping("/spring-retry")
    public void callRetryService() throws SQLException {
        springRetryService.execute();
    }
}
