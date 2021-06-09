package com.example.builder.controller.json.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JsonDto {

    @JsonProperty("name")
    private String name;
}
