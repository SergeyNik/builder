package com.example.builder.service.integration;

import org.springframework.messaging.Message;

public interface Viewer {

    void send(Message<?> message);

}
