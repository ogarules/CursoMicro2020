package com.example.demo.controllers;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AuthorController {
    
    @Autowired
    AuthorRepository repository;

    @GetMapping("/author")
    public List<Author> getAllAuthors() {
        return this.repository.findAll();
    }
    
    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable Integer id) throws ResourceNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee"));
    }
    
    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author entity) {
        return this.repository.save(entity);
    }
    
    @PutMapping("/author/{id}")
    public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author) throws ResourceNotFoundException {
        Author authorBd = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee"));
        authorBd.setDob(author.getDob());
        authorBd.setFamilyName(author.getFamilyName());
        authorBd.setLastName(author.getLastName());
        authorBd.setName(author.getName());

        return this.repository.save(authorBd);
    }
}
