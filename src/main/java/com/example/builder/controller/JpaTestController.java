package com.example.builder.controller;

import com.example.builder.model.Car;
import com.example.builder.model.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JpaTestController {

    private final CarRepository carRepository;

    @GetMapping("/car-by-asc")
    public void callRetryService() {
        Car car = carRepository.findFirstByOrderByCreatedAtAsc();
        log.info("My cat is: {}", car);
    }
}
