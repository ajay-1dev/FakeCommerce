package com.example.FakeCommerce.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.OrderProductsDto;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.services.OrderProductsService;
import com.example.FakeCommerce.utils.ApiResponse;

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

    @GetMapping()
    public ResponseEntity<ApiResponse<List<OrderProducts>>> getAllOrderProducts() {
        return ResponseEntity.ok(ApiResponse.success(orderProductsService.getAllOrderProducts(), "Fetched All orderproducts"));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<OrderProducts>> getOrderProductsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(orderProductsService.getOrderProductById(id), "Fetched Sucessfully with this is : "+id));
    }
    
    @PostMapping()
    public ResponseEntity<ApiResponse<OrderProducts>> createOrderProducts(@RequestBody OrderProductsDto entity) {        
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(orderProductsService.createOrderProducts(entity), "Fetched Sucessfully"));
    }
    
    
}
