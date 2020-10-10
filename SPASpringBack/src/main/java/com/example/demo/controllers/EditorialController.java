package com.example.demo.controllers;

import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Editorial;
import com.example.demo.repositories.EditorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditorialController {
    
    @Autowired
    EditorialRepository repository;

    @GetMapping("/editorial")
    public List<Editorial> getAllAuthors() {
        return this.repository.findAll();
    }
    
    @GetMapping("/editorial/{id}")
    public Editorial getAuthor(@PathVariable Integer id) throws ResourceNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee"));
    }
    
    @PostMapping("/editorial")
    public Editorial addAuthor(@RequestBody Editorial entity) {
        return this.repository.save(entity);
    }
    
    @PutMapping("/editorial/{id}")
    public Editorial updateAuthor(@PathVariable Integer id, @RequestBody Editorial editorial) throws ResourceNotFoundException {
        Editorial editorialBd = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee"));
        editorialBd.setAddress(editorial.getAddress());
        editorialBd.setName(editorial.getName());

        return this.repository.save(editorialBd);
    }
}
