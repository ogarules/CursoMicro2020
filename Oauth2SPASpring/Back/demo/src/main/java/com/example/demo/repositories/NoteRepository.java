package com.example.demo.repositories;

import java.util.List;

import com.example.demo.models.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUserId(String userId);
}