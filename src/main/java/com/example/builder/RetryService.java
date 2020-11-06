package com.example.builder;

import java.sql.SQLException;

public interface RetryService {

    void execute() throws SQLException;
}
