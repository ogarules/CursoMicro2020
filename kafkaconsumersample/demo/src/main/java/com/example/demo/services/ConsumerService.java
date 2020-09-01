package com.example.demo.services;

import com.example.demo.models.Note;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "notes", groupId = "group-id")
    public void consume(Note message){
        logger.info("Mensaje consumido : " + message.toString());
    }
}