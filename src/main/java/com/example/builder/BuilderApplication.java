package com.example.builder;

import com.example.builder.config.ApplicationSettings;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;

@EnableAdminServer
@EnableRetry
@SpringBootApplication
@ImportResource("integration-context.xml")
public class BuilderApplication implements ApplicationRunner {

    @Autowired
    private ApplicationSettings settings;

    public static void main(String[] args) {
        SpringApplication.run(BuilderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String pass = settings.getPass();
        System.out.println(pass);
    }
}
