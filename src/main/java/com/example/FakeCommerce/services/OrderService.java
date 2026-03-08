package com.example.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.OrderDto;
import com.example.FakeCommerce.repositiories.OrderRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order createOrder(OrderDto order){
        Order dummy = Order.builder()
        .status(OrderStatus.valueOf(order.getStatus()))
        .build();
        return orderRepository.save(dummy);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(
            () -> new RuntimeException("order not found"));
    }
}
