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
    public ResponseEntity<ApiResponse<List<Order>>> getOrders() {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(orderService.getAllOrders()," Order Created sucess fully"));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrderById(id),"the order details of the id :"+id));
    }
    

    @PostMapping()
    public ResponseEntity<ApiResponse<GetOrderResponseDTO>> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {   

        GetOrderResponseDTO response = orderService.createOrder(createOrderRequestDTO);  
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.success(response,"Created Sucessfully"));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<GetOrderResponseDTO>> updateOrder(@PathVariable("id") Long id , @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO){
        GetOrderResponseDTO response = orderService.updateOrder(id, updateOrderRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.success(response,"Sucessfully updated"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Deleted sucessfully with this id"+id);
    }
}
