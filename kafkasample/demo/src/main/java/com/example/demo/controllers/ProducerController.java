package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.services.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/producer")
public class ProducerController {
    @Autowired
    Producer producer;

    @PostMapping(value="/publish/{message}")
    public void sendToKafka(@PathVariable String message) {
        Note note = new Note();
        note.setId(1);
        note.setTitle(message);

        this.producer.sendMessage(note);
    }
    
}