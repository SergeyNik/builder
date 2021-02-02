package com.example.builder.repository;

import com.example.builder.model.Owner;
import com.example.builder.model.QOwner;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID>,
        QuerydslPredicateExecutor<Owner>,
        QuerydslBinderCustomizer<QOwner> {

    @Override
    default public void customize(QuerydslBindings bindings, QOwner root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
