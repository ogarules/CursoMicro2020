package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Editorial;
import com.example.demo.repositories.EditorialRepository;
import com.example.demo.to.EditorialTO;

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
    public List<EditorialTO> getAllAuthors() {
        return this.repository.findAll().stream()
                                        .map(r -> new EditorialTO(r.getId(), r.getName(), r.getAddress()))
                                        .collect(Collectors.toList());
    }
    
    @GetMapping("/editorial/{id}")
    public EditorialTO getAuthor(@PathVariable Integer id) throws ResourceNotFoundException {
        Editorial editorialSaved = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee"));
        return new EditorialTO(editorialSaved.getId(), editorialSaved.getName(), editorialSaved.getAddress());
    }
    
    @PostMapping("/editorial")
    public EditorialTO addAuthor(@RequestBody Editorial entity) {
        Editorial editorialSaved = this.repository.save(entity);
        return new EditorialTO(editorialSaved.getId(), editorialSaved.getName(), editorialSaved.getAddress());
    }
    
    @PutMapping("/editorial/{id}")
    public EditorialTO updateAuthor(@PathVariable Integer id, @RequestBody Editorial editorial) throws ResourceNotFoundException {
        Editorial editorialBd = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la editorial weee"));
        editorialBd.setAddress(editorial.getAddress());
        editorialBd.setName(editorial.getName());

        Editorial editorialSaved = this.repository.save(editorialBd);
        return new EditorialTO(editorialSaved.getId(), editorialSaved.getName(), editorialSaved.getAddress());
    }
}
