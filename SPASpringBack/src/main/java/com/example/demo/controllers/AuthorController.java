package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.to.AuthorTO;

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
    public List<AuthorTO> getAllAuthors() {
        return this.repository.findAll().stream()
                                        .map(r -> new AuthorTO(r.getId(), r.getName(), r.getLastName(), r.getFamilyName(), r.getDob()))
                                        .collect(Collectors.toList());
    }
    
    @GetMapping("/author/{id}")
    public AuthorTO getAuthor(@PathVariable Integer id) throws ResourceNotFoundException {
        Author authorSaved = this.repository.findById(id)
                              .orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee"));
        return new AuthorTO(authorSaved.getId(), authorSaved.getName(), authorSaved.getLastName(), authorSaved.getFamilyName(), authorSaved.getDob());
    }
    
    @PostMapping("/author")
    public AuthorTO addAuthor(@RequestBody Author entity) {
        Author authorSaved = this.repository.save(entity);
        return new AuthorTO(authorSaved.getId(), authorSaved.getName(), authorSaved.getLastName(), authorSaved.getFamilyName(), authorSaved.getDob());
    }
    
    @PutMapping("/author/{id}")
    public AuthorTO updateAuthor(@PathVariable Integer id, @RequestBody Author author) throws ResourceNotFoundException {
        Author authorBd = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el autor weee"));
        authorBd.setDob(author.getDob());
        authorBd.setFamilyName(author.getFamilyName());
        authorBd.setLastName(author.getLastName());
        authorBd.setName(author.getName());

        Author authorSaved = this.repository.save(authorBd);
        return new AuthorTO(authorSaved.getId(), authorSaved.getName(), authorSaved.getLastName(), authorSaved.getFamilyName(), authorSaved.getDob());
    }
}
