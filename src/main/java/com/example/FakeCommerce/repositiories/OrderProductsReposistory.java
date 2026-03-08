package com.example.FakeCommerce.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.OrderProducts;

@Repository
public interface OrderProductsReposistory extends JpaRepository<OrderProducts,Long> {
    
}
