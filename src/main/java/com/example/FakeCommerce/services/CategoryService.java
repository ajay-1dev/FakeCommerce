package com.example.FakeCommerce.services;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.repositiories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;

import lombok.RequiredArgsConstructor;

import java.util.*;

import com.example.FakeCommerce.schema.*;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto requestdto){

        Category newCategory = Category.builder()
        .name(requestdto.getName())
        .build();
        return categoryRepository.save(newCategory);
    }

    public  List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
