package com.example.builder.service;

import com.example.builder.model.Car;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.UUID;

public interface CarService {

    List<Car> findAll();

    List<Car> findFetchedAll();

    Car findFirstByOrderByCreatedAtAsc();

    Iterable<Car> findByOrderByCreated(Predicate predicate);

    UUID findDocumentId();
}
