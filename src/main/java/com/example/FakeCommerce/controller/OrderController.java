package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.OrderDto;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.services.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    
    private final OrderService orderService;

    @GetMapping()
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }
    

    @PostMapping()
    public Order createOrder(@RequestBody OrderDto order) {        
        return orderService.createOrder(order);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        System.out.println("Order Sucessfully Deleted");
    }
}
