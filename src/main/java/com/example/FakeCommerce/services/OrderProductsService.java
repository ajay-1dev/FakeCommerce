package com.example.FakeCommerce.services;

import java.util.List;

import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.OrderProductsDto;
import com.example.FakeCommerce.repositiories.OrderProductsReposistory;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProductsService {
    private final OrderProductsReposistory orderProductsReposistory;
    private final OrderService orderService;
    private final ProductService productService;

    public List<OrderProducts> getAllOrderProducts(){
        return orderProductsReposistory.findAll();
    }

    public OrderProducts createOrderProducts(OrderProductsDto orderproducts){
        Order order = orderService.getOrderById(orderproducts.getOrder_id());
        Product product = productService.getProductById(orderproducts.getProduct_id());
        OrderProducts dummy = OrderProducts.builder()
        .quantity(orderproducts.getQuantity())
        .order(order)
        .product(product) 
        .build();
        return orderProductsReposistory.save(dummy);
    }

    public OrderProducts getOrderProductById(Long id){
        return orderProductsReposistory.findById(id).orElseThrow(
            () -> new RuntimeException("orderproduct not found"));
    }
}
