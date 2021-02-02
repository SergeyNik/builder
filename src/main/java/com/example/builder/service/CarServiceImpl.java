package com.example.builder.service;

import com.example.builder.model.Car;
import com.example.builder.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findFetchedAll() {
        return carRepository.findCarsAndOwners();
    }

    @Override
    public Car findFirstByOrderByCreatedAtAsc() {
        return carRepository.findFirstByOrderByCreatedAtAsc();
    }

    @Override
    public UUID findDocumentId() {
        return carRepository.findDocumentId();
    }
}
