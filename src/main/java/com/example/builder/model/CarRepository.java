package com.example.builder.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Car findFirstByOrderByCreatedAtAsc();

    Car findTopByNameOrderByCreatedAtDesc(String name);

    @Query(value = "SELECT CAST(d.id as varchar) " +
            "FROM car c " +
            "JOIN owner o ON c.owner_id = o.id " +
            "JOIN document d ON o.document_id = d.id " +
            "WHERE d.name = 'passport' " +
            "ORDER BY c.created_at LIMIT 1", nativeQuery = true)
    UUID findDocumentId();

}
