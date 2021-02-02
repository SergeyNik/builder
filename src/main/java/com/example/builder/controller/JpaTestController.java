package com.example.builder.controller;

import com.example.builder.model.Car;
import com.example.builder.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JpaTestController {

    private final CarService carService;

    @GetMapping("/car-by-asc")
    public void getCarByAsc() {
        Car car = carService.findFirstByOrderByCreatedAtAsc();
        log.info("My cat is: {}", car);
    }

    @GetMapping("/cars")
    public void getCars() { // N + 1
        List<Car> cars = carService.findAll();
        for (Car car : cars) {
            log.info("My car is: {}", car.getCost());
            log.info("Owner id is: {}", car.getOwner().getId());
            log.info("Document id is: {}", car.getOwner().getDocument().getId()); // throw
            log.info("Document is: {}", car.getOwner().getDocument()); // throw
            log.info("Owner is: {}", car.getOwner()); // throw
            log.info("Owner name is: {}", car.getOwner().getName()); // throw
        }
    }

    @GetMapping("/fetched-cars")
    public void getFetchedCars() {
        List<Car> cars = carService.findFetchedAll();
        for (Car car : cars) {
            log.info("My car is: {}", car.getCost());
            log.info("Owner id is: {}", car.getOwner().getId());
            log.info("Owner is: {}", car.getOwner());
            log.info("Owner name is: {}", car.getOwner().getName());
        }
    }

    @GetMapping("/car-document")
    public void findDocId() {
        UUID documentId = carService.findDocumentId();
        log.info("DocId is: {}", documentId);
    }
}
