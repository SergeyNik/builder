package com.example.builder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpringJob {

    @Scheduled(cron = "*/10 * * * * ?")
    public void doJob() {
        log.info("Задача по расписанию");
    }
}
