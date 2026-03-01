package com.example.FakeCommerce.repositiories;

import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Category;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
}
