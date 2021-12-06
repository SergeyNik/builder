package com.example.builder.controller;

import com.example.builder.service.integration.Viewer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SwaggerDefinition(tags = {
        @Tag(name = "spring-integration-controller", description = "Запрос на выгрузку иерархии кодов из MDLP")
})
@Api(tags = {"spring-integration-controller"})
@RestController
public class SpringIntegrationController {

    @Autowired
    private Viewer viewer;

    @ApiOperation(value = "Получить заявки по страницам", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    @GetMapping("/http-integration")
    public void callRetryService() {
        for (int i = 0; i < 10; i++) {
            viewer.send(MessageBuilder.withPayload("google").build());
        }
    }
}
