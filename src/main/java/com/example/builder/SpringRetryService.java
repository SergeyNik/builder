package com.example.builder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Slf4j
@Service
public class SpringRetryService implements RetryService {

    private int counter = 0;

    @Retryable(value = {SQLException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    public String execute() throws SQLException {
        counter++;
        log.info("Billing Service Failed " + counter);
        throw new SQLException();
    }

    @Recover
    public String recover(SQLException t) {
        log.info("Service recovering");
        return "Service recovered from billing service failure.";
    }
}