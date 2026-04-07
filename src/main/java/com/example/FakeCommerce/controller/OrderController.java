package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateOrderRequestDTO;
import com.example.FakeCommerce.dtos.GetOrderResponseDTO;
import com.example.FakeCommerce.dtos.OrderDto;
import com.example.FakeCommerce.dtos.UpdateOrderRequestDTO;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.services.OrderService;
import com.example.FakeCommerce.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
    public GetOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {        
        return orderService.createOrder(createOrderRequestDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<GetOrderResponseDTO>> updateOrder(@PathVariable("id") Long id , @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO){
        GetOrderResponseDTO response = orderService.updateOrder(id, updateOrderRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.success(response,"Sucessfully updated"));
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        System.out.println("Order Sucessfully Deleted");
    }
}
