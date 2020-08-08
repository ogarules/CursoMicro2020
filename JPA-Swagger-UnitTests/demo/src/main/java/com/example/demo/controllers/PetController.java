package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "pet")
public class PetController {
    @Autowired
    PetRepository repository;

    @Autowired
    SmartValidator validator;

    @PostMapping
    public Object postMethodName(@RequestBody Pet entity, BindingResult result) {
        
        this.validator.validate(entity, result);
        if(result.hasErrors())
        {
            return new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
        }

        Pet pet = this.repository.save(entity);
        return pet;
    }
    
    @GetMapping
    public Iterable<Pet> getAllPets() {
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Pet> getPet(@PathVariable Integer id) {
        return this.repository.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        this.repository.deleteById(id);
    }
}