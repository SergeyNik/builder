package com.example.builder.service.retry;

import com.example.builder.service.retry.RetryService;
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
    public void execute() throws SQLException {
        counter++;
        log.info("Billing Service Failed " + counter);
        throw new SQLException();
    }

    @Override
    public void executeRetryRegistry() {
        throw new UnsupportedOperationException();
    }

    @Recover
    public void recover(SQLException t) {
        log.info("Service recovering");
    }
}