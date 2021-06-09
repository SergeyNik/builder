package com.example.builder.controller.json;

import com.example.builder.controller.json.dto.JsonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JsonConvertController {

    private static final String json = "fixtures/json.json";
    private final ObjectMapper mapper;

    @GetMapping("/json-to-object")
    public void convertJsonToObject() throws IOException {
        URL path = new ClassPathResource(json).getURL();
        JsonDto jsonDto = mapper.readValue(path, JsonDto.class);
        System.out.println(jsonDto);
    }
}
