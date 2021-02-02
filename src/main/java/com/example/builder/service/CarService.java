package com.example.builder.service;

import com.example.builder.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<Car> findAll();

    List<Car> findFetchedAll();

    Car findFirstByOrderByCreatedAtAsc();

    UUID findDocumentId();
}
