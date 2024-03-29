package com.example.builder.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationSettings {

    private String pass;
    private String name;
    private String version;
}
