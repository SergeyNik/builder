package com.example.builder.service.integration;

import org.springframework.messaging.Message;

public interface PageViewer {

    void show(Message<String> message);
}
