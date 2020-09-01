package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Note;
import com.example.demo.repositories.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "notes")
public class NoteController {
    @Autowired
    NoteRepository repository;

    @GetMapping()
    public List<Note> getNotes() {
        return this.repository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Note> getNote(@PathVariable Integer id) {
        return this.repository.findById(id);
    }
    
    @PostMapping()
    public Note addNote(@RequestBody Note entity) {
        return this.repository.save(entity);
    }

    @PutMapping(value="{id}")
    public Note updateNote(@PathVariable Integer id, @RequestBody Note entity) {
        return this.repository.save(entity);
    }
    
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Integer id) {
        this.repository.deleteById(id);
    }
 
    @GetMapping("/user")
    public List<Note> getNotesByUser(@AuthenticationPrincipal OidcUser user) {
        return this.repository.findByUserId(user.getPreferredUsername());
    }
}