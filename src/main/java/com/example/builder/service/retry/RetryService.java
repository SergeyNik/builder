package com.example.builder.service.retry;

import java.sql.SQLException;

public interface RetryService {

    void execute() throws SQLException;

    void executeRetryRegistry();
}
