package com.example.courses.controllers;

import java.util.List;

import com.example.courses.exception.ResourseNotFoundException;
import com.example.courses.models.Instructor;
import com.example.courses.repositories.InstructorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorRepository repository;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) throws ResourseNotFoundException {
        Instructor instructor = this.repository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Instructor not found"));
        return ResponseEntity.ok().body(instructor);
    }
    
    @PostMapping
    public Instructor saveInstructor(@RequestBody Instructor entity) {
        return this.repository.save(entity);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateUser(@PathVariable Long id, @RequestBody Instructor entity) throws ResourseNotFoundException{
        Instructor instructor = this.repository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Instructor not found"));
        instructor.setFirstName(entity.getFirstName());
        instructor.setLastName(entity.getLastName());
        instructor.setEmail(entity.getEmail());

        Instructor savedInstructor = this.repository.save(instructor);

        return ResponseEntity.ok().body(savedInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> deleteInstructor(@PathVariable Long id) throws ResourseNotFoundException{
        Instructor instructor = this.repository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Instructor not found"));

        this.repository.delete(instructor);
        return ResponseEntity.ok().body(instructor);
    }
}
