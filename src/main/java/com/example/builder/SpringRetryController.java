package com.example.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class SpringRetryController {

    private final RetryService retryService;

    @GetMapping("/spring-retry")
    public String callRetryService() throws SQLException {
        return retryService.execute();
    }
}
