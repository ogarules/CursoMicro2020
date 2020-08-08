package com.example.demo.repositories;

import com.example.demo.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Integer> {
    
}