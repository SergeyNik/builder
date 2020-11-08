package com.example.builder.service.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PageViewerImpl implements PageViewer {

    @Override
    public void show(Message<String> message) {
        log.info("Response from google = " + message.getPayload());
    }
}
