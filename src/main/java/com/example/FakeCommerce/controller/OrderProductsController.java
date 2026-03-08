package com.example.FakeCommerce.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.OrderProductsDto;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.services.OrderProductsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orderproducts")
public class OrderProductsController{
 
    private final OrderProductsService orderProductsService;

    @GetMapping("")
    public List<OrderProducts> getAllOrderProducts() {
        return orderProductsService.getAllOrderProducts();
    }

    @GetMapping("{id}")
    public OrderProducts getOrderProductsById(@PathVariable("id") Long id) {
        return orderProductsService.getOrderProductById(id);
    }
    
    @PostMapping("")
    public OrderProducts createOrderProducts(@RequestBody OrderProductsDto entity) {        
        return orderProductsService.createOrderProducts(entity);
    }
    
    
}
