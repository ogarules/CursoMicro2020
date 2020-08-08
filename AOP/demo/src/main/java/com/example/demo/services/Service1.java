package com.example.demo.services;

import com.example.demo.dao.Dao1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service1 {
    private Logger LOGGER = LoggerFactory.getLogger(Service1.class);

    @Autowired
    private Dao1 dao;

    public String getSomething(){
        String result = this.dao.getData();
        LOGGER.info("Inside Service1");

        return result;
    }
}