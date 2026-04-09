package com.example.FakeCommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateCategoryRequestDto;
import com.example.FakeCommerce.schema.Category;
import com.example.FakeCommerce.services.CategoryService;
import com.example.FakeCommerce.utils.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequestDto requestDto) {
        return  ResponseEntity
        .status(HttpStatus.CREATED)
        .body(categoryService.createCategory(requestDto));
    }
    
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.success(categoryService.getAllCategories(), "null"));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Category>> getcategoryById(@PathVariable("id") Long id){
        return ResponseEntity.status(0)
        .body(ApiResponse.success(categoryService.getCategoryById(id),""));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted Sucesfully");
}
}