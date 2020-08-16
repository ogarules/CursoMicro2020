package com.example.demo.repositories;

import com.example.demo.models.Detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DetailRepository extends JpaRepository<Detail, Integer> {
    
}