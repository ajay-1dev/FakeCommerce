package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.dtos.EditProductDto;
import com.example.FakeCommerce.dtos.ProductDetailsResponceIdDto;
import com.example.FakeCommerce.dtos.ProductResponceDto;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.services.ProductService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/dto")
    public List<ProductResponceDto> getAllProductsDto() {
        return productService.getProductDto();
    }

    @GetMapping("/dto/{id}")
    public ProductDetailsResponceIdDto getMethodName(@PathVariable("id") Long id) {
        return productService.getProductDtoById(id);
    }
    

    @GetMapping("/p")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("categories/{id}")
    public List<Product> getProductsByCategory(@PathVariable("id") Long id){
        return productService.getProductByCategory(id);
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto dto) {
        return productService.createProduct(dto);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "product delted successfully";
    }
   
    @PutMapping("edit/{id}")
    public EditProductDto putMethodName(@PathVariable("id") Long id, @RequestBody EditProductDto dto) {
        return productService.editProduct(id, dto);
    }

}
