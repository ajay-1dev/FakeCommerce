package com.example.FakeCommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateOrderRequestDTO;
import com.example.FakeCommerce.dtos.OrderDto;
import com.example.FakeCommerce.exeptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositiories.OrderProductsReposistory;
import com.example.FakeCommerce.repositiories.OrderRepository;
import com.example.FakeCommerce.repositiories.ProductRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.schema.OrderStatus;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductsReposistory orderProductsReposistory;
    private final ProductRepository productRepository;
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void createOrder(CreateOrderRequestDTO createOrderRequestDTO){
        /*Order dummy = Order.builder()
        .status(OrderStatus.valueOf(order.getStatus()))
        .build();
        return orderRepository.save(dummy);
        */
        Order order = Order.builder()
        .status(OrderStatus.PENDING)
        .build();
        orderRepository.save(order);
        if(createOrderRequestDTO.getOrderItems() != null){
        for(var itemDto : createOrderRequestDTO.getOrderItems()){
            Product product = productRepository.findById(itemDto.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product with this id : "+itemDto.getProductId()+" Not found "));
            OrderProducts orderProducts = OrderProducts.builder()
            .order(order)
            .product(product)
            .quantity(itemDto.getQuantity() != null ? itemDto.getQuantity():1)
            .build();
            orderProductsReposistory.save(orderProducts);
        }
    }
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(
            () -> new RuntimeException("order not found"));
    }
}
