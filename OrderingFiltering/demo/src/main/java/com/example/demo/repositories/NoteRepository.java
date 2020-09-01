package com.example.demo.repositories;

import com.example.demo.models.Note;
import com.example.demo.models.QNote;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>, QuerydslPredicateExecutor<Note>, QuerydslBinderCustomizer<QNote> {
    @Override
    default public void customize(QuerydslBindings bindings, QNote root){
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }    
}