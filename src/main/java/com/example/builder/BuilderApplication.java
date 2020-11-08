package com.example.builder;

import com.example.builder.service.integration.Viewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@ImportResource("integration-context.xml")
public class BuilderApplication implements ApplicationRunner {

    @Autowired
    private Viewer viewer;

    public static void main(String[] args) {
        SpringApplication.run(BuilderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            viewer.send(MessageBuilder.withPayload("google").build());
        }
    }
}
