package com.example.FakeCommerce.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProducts;

@Repository
public interface OrderProductsReposistory extends JpaRepository<OrderProducts,Long> {

    //JPQL method
    List<OrderProducts> findByOrderId(Long orderId);

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.product WHERE op.order = :order")
    List<OrderProducts> findByOrderWithProduct(@Param("order")Order order);
}
