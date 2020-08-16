package com.example.demo.controllers;

import com.example.demo.models.Order;
import com.example.demo.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="store")
public class StoreController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping(value="/order/{id}")
    public Order getMethodName(@PathVariable Integer id) {
        return this.ordersService.getOrder(id);
    }
    
}