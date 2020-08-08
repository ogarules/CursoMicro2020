package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class Dao2 {
    public String getOtherData(String value){
        return "dao2 -> " + value;
    }
} 