package com.example.FakeCommerce.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.OrderAdapter;
import com.example.FakeCommerce.dtos.CreateOrderRequestDTO;
import com.example.FakeCommerce.dtos.GetOrderResponseDTO;
import com.example.FakeCommerce.dtos.OrderDto;
import com.example.FakeCommerce.dtos.OrderItemActionDTO;
import com.example.FakeCommerce.dtos.UpdateOrderRequestDTO;
import com.example.FakeCommerce.exeptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositiories.OrderProductsReposistory;
import com.example.FakeCommerce.repositiories.OrderRepository;
import com.example.FakeCommerce.repositiories.ProductRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.schema.OrderStatus;
import com.example.FakeCommerce.schema.Product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductsReposistory orderProductsReposistory;
    private final ProductRepository productRepository;
    private final OrderAdapter orderAdapter;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public GetOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO){
        /*Order dummy = Order.builder()
        .status(OrderStatus.valueOf(order.getStatus()))
        .build();
        return orderRepository.save(dummy);
        */

        
        Order order = Order.builder()
        .status(OrderStatus.PENDING)
        .build();
        orderRepository.save(order);

        /*
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
            */
           

        if(createOrderRequestDTO.getOrderItems() != null){
        List<Long> productIds = createOrderRequestDTO.getOrderItems().stream().map(items -> items.getProductId()).toList();
        List<Product> products = productRepository.findAllById(productIds);
        
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId , Function.identity()));

        for(Long id :productIds){
            if(!productMap.containsKey(id)){
                throw new ResourceNotFoundException("Product not found with this id :"+id);
            }
        }
        List<OrderProducts> orderProducts = new ArrayList<>();
        for(var itemsDto : createOrderRequestDTO.getOrderItems()){
            Product product = productMap.get(itemsDto.getProductId());
            OrderProducts orderProductsref = OrderProducts.builder()
            .order(order)
            .product(product)
            .quantity(itemsDto.getQuantity() != null ? itemsDto.getQuantity():1)
            .build();

            orderProducts.add(orderProductsref);

        }
        orderProductsReposistory.saveAll(orderProducts);

    }
            return orderAdapter.mapToGetOrderResponseDto(order);
    }

    @Transactional
    public GetOrderResponseDTO updateOrder(Long id,UpdateOrderRequestDTO updateOrderRequestDTO){
        Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with this id : "+id));

        if(updateOrderRequestDTO.getStatus() != null){
            order.setStatus(updateOrderRequestDTO.getStatus());
            orderRepository.save(order);
        }


        List<OrderProducts> toSave = new ArrayList<>();
        List<OrderProducts> toDelete = new ArrayList<>();
        
        if(updateOrderRequestDTO.getOrderItems() != null){
        List<Long> productIds = updateOrderRequestDTO.getOrderItems().stream().map(items -> items.getProductId()).toList();

        List<Product> products = productRepository.findAllById(productIds);

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
        
        for(var pid : productIds){
            if(!productMap.containsKey(pid)){
                throw new ResourceNotFoundException("Product not foound with this id : "+pid);
            }
        }


        
        Map<Long , OrderProducts> existingItems = orderProductsReposistory.findByOrderWithProduct(order).stream().collect(Collectors.toMap(op -> op.getProduct().getId(),Function.identity()));

        for(OrderItemActionDTO itemAction : updateOrderRequestDTO.getOrderItems()){
            Product product = productMap.get(itemAction.getProductId());
            
            OrderProducts existing = existingItems.get(product.getId());

            switch(itemAction.getAction()){
                case ADD -> {
                    if(existing != null){
                        int addQuantity = itemAction.getQuantity()!=null?itemAction.getQuantity():1;
                        existing.setQuantity(existing.getQuantity()+addQuantity);
                        toSave.add(existing);
                    }
                    else{
                        OrderProducts newItem = OrderProducts.builder()
                        .order(order)
                        .product(product)
                        .quantity(itemAction.getQuantity() != null ?itemAction.getQuantity():1)
                        .build();
                        toSave.add(newItem);
                        existingItems.put(product.getId(), newItem);
                    }
                }

                case REMOVE ->{
                  if(existing == null) {
                            throw new ResourceNotFoundException("Product not found with id: " + product.getId());
                        }
                        toDelete.add(existing);
                        existingItems.remove(product.getId());   
                }
                case INCREMENT -> {
                    if(existing == null) {
                        throw new ResourceNotFoundException("Product not found with id: " + product.getId());
                        }
                    existing.setQuantity(existing.getQuantity() + 1);
                    toSave.add(existing);

                }
                case DECREMENT -> {
                    if(existing == null)
                        throw new ResourceNotFoundException("Product not found with this id: "+id);
                    if(existing.getQuantity()<=1){
                        toDelete.add(existing);
                        existingItems.remove(product.getId());
                    }
                    else{
                        existing.setQuantity(existing.getQuantity() -1);
                        toSave.add(existing);
                    }
                }
            }
        }
      
    }
        if(!toSave.isEmpty())
        orderProductsReposistory.saveAll(toSave);

        if(!toDelete.isEmpty())
        orderProductsReposistory.deleteAll(toDelete);

        return orderAdapter.mapToGetOrderResponseDto(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(
            () -> new RuntimeException("order not found"));
    }
}
