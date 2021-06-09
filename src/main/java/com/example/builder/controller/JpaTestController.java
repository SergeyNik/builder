package com.example.builder.controller;

import com.example.builder.model.Car;
import com.example.builder.model.QCar;
import com.example.builder.service.CarService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JpaTestController {

    private final CarService carService;

    @GetMapping("/car-by-asc")
    public void getCarByAsc() {
        log.info("This class {}", this.getClass().getName());
        Car car = carService.findFirstByOrderByCreatedAtAsc();
        log.info("My car is: {}", car);
    }

    @GetMapping("/car-by-created")
    public Iterable<Car> getCarByCreatedAt(
            @QuerydslPredicate(root = Car.class) Predicate predicate,
            @RequestParam(value = "createdAt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime createdAt
    ) {
        BooleanBuilder builder = new BooleanBuilder(predicate);
        if (nonNull(createdAt)) {
            builder.and(QCar.car.createdAt.before(createdAt));
        }
        Iterable<Car> byOrderByCreated = carService.findByOrderByCreated(builder);
        for (Car car : byOrderByCreated) {
            log.info("My car is: {}", car);
        }
        return byOrderByCreated;
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
    @ResponseStatus(HttpStatus.OK)
    public void findDocId() {
        UUID documentId = carService.findDocumentId();
        log.info("DocId is: {}", documentId);
    }
}
