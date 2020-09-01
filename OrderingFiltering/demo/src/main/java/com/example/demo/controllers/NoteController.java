package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.repositories.NoteRepository;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("notes")
public class NoteController {
    @Autowired
    NoteRepository repository;

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Note> requestMethodName(
        @QuerydslPredicate(root=Note.class) Predicate predicate,
        @SortDefault(sort="text", direction = org.springframework.data.domain.Sort.Direction.ASC) @PageableDefault(value = 2, page = 0) Pageable pageable
    ) {
        return this.repository.findAll(predicate, pageable);
    }
    
}