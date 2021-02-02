package com.example.builder.repository;

import com.example.builder.model.Document;
import com.example.builder.model.QDocument;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID>,
        QuerydslPredicateExecutor<Document>,
        QuerydslBinderCustomizer<QDocument> {

    @Override
    default public void customize(QuerydslBindings bindings, QDocument root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
