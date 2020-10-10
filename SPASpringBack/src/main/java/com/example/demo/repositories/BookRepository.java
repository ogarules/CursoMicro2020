package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(Integer authorId);
    List<Book> findByEditorialId(Integer ditorialId);
    Optional<Book> findByIdAndAuthorId(Integer id, Integer authorId);
    Optional<Book> findByIdAndEditorialId(Integer id, Integer editorial);
}
