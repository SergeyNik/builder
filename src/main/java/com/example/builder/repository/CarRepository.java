package com.example.builder.repository;

import com.example.builder.model.Car;
import com.example.builder.model.QCar;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID>,
        QuerydslPredicateExecutor<Car>,
        QuerydslBinderCustomizer<QCar> {

    @Override
    default public void customize(QuerydslBindings bindings, QCar root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    Car findFirstByOrderByCreatedAtAsc();

    Car findTopByNameOrderByCreatedAtDesc(String name);

    @Query(value = "SELECT CAST(d.id as varchar) " +
            "FROM car c " +
            "JOIN owner o ON c.owner_id = o.id " +
            "JOIN document d ON o.document_id = d.id " +
            "WHERE d.name = 'passport' " +
            "ORDER BY c.created_at LIMIT 1", nativeQuery = true)
    UUID findDocumentId();

    @Query(value = "SELECT c.* FROM car c JOIN owner o ON c.owner_id = o.id", nativeQuery = true)
    List<Car> findCarsAndOwners();
}
