package com.example.demo.services;

import com.example.demo.models.Note;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String topic= "notes";

    @Autowired
    private KafkaTemplate<String, Note> kafkaTemplate;

    public void sendMessage(Note message){
        
        logger.info("Produciendo mensaje : " + message.toString());
        this.kafkaTemplate.send(topic, message);
    }
}