package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Book;
import com.example.demo.models.QBook;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface BookRepository extends JpaRepository<Book, Integer>, QuerydslPredicateExecutor<Book>, QuerydslBinderCustomizer<QBook> {
    List<Book> findByAuthorId(Integer authorId);
    List<Book> findByEditorialId(Integer ditorialId);
    Optional<Book> findByIdAndAuthorId(Integer id, Integer authorId);
    Optional<Book> findByIdAndEditorialId(Integer id, Integer editorial);

    @Override
    default public void customize(QuerydslBindings bindings, QBook root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        
    }
}
