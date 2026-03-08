package com.example.FakeCommerce.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.FakeCommerce.schema.Review;
@Repository
public interface ReviewReposistory extends JpaRepository<Review,Long> {
    
} 
