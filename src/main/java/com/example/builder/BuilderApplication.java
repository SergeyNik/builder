package com.example.builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@ImportResource("integration-context.xml")
public class BuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuilderApplication.class, args);
    }
}
