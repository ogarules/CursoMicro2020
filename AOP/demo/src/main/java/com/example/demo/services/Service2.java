package com.example.demo.services;

import com.example.demo.dao.Dao2;
import com.example.demo.models.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service2 {
    private Logger LOGGER = LoggerFactory.getLogger(Service2.class);

    @Autowired
    private Dao2 dao;

    //@MyAround
    public String getSomething(String param){
        String result = this.dao.getOtherData(param);
        LOGGER.info("Inside Service 2");

        return result;
    }

    public Person getPerson(Person param){
        String result = param.getName() + " " + param.getLastName(); 
        LOGGER.info("Inside Service 2");

        return param;
    }

    public void getPerson2(Person param){
        String result = param.getName() + " " + param.getLastName(); 
        LOGGER.info("Inside Service 2");
    }
}