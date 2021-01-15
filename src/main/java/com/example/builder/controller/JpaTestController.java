package com.example.builder.controller;

import com.example.builder.model.Car;
import com.example.builder.model.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JpaTestController {

    private final CarRepository carRepository;

    @GetMapping("/car-by-asc")
    public void getCarByAsc() {
        Car car = carRepository.findFirstByOrderByCreatedAtAsc();
        log.info("My cat is: {}", car);
    }

    @GetMapping("/cars-by-asc")
    public void getCarsByAsc() {
        Car car = carRepository.findFirstByOrderByCreatedAtAsc();
        log.info("My cat is: {}", car);
    }

    @GetMapping("/cars")
    public void getCars() { // N + 1
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            log.info("My car is: {}", car.getCost());
            log.info("Owner id is: {}", car.getOwner().getId());
            log.info("Document id is: {}", car.getOwner().getDocument().getId()); // throw
            log.info("Document is: {}", car.getOwner().getDocument()); // throw
            log.info("Owner is: {}", car.getOwner()); // throw
            log.info("Owner name is: {}", car.getOwner().getName()); // throw
        }
    }
}
