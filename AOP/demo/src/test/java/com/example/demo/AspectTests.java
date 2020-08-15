package com.example.demo;

import com.example.demo.models.Person;
import com.example.demo.services.Service1;
import com.example.demo.services.Service2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTests {
    private Logger LOGGER = LoggerFactory.getLogger(AspectTests.class);
    
    @Autowired
    Service1 service1;

    @Autowired
    Service2 service2;

    @Test
    public void aspectTest(){
        Person person = new Person();
        person.setName("OSCAR");
        person.setLastName("GARCIA");

        Person result = service2.getPerson(person);
        LOGGER.info(result.getAge().toString());
        service2.getPerson2(person);
    }
}