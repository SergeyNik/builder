package com.example.builder.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Car findFirstByOrderByCreatedAtAsc();

    Car findTopByNameOrderByCreatedAtDesc(String name);
}
