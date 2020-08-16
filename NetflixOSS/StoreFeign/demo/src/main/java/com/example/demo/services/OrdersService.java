package com.example.demo.services;

import com.example.demo.models.Order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("orders-service")
public interface OrdersService {
 
    @RequestMapping("/orders/{id}")
    Order getOrder(@PathVariable Integer id);

    @PostMapping("/orders")
    Order addOrder(@RequestBody Order order);
}