package com.example.builder;

import java.sql.SQLException;

public interface RetryService {

    String execute() throws SQLException;
}
