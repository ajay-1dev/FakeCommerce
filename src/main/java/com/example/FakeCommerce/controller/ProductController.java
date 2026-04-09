package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.dtos.EditProductDto;
import com.example.FakeCommerce.dtos.ProductDetailsResponceIdDto;
import com.example.FakeCommerce.dtos.ProductResponceDto;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.services.ProductService;
import com.example.FakeCommerce.utils.ApiResponse;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(productService.getAllProducts(), "All Products Details"));
    }

    @GetMapping("/dto")
    public ResponseEntity<ApiResponse<List<ProductResponceDto>>> getAllProductsDto() {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductDto(),"Products details"));
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ApiResponse<ProductDetailsResponceIdDto>> getMethodName(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductDtoById(id), "Product details of the id : "+id));
    }
    

    @GetMapping("/p")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        return ResponseEntity.ok(ApiResponse.success(productService.getProducts(),"All products details"));
    }
    

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductById(id), "Product details of the id : "+id));
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.getProductByCategory(id),"Fetching details with category id"));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody CreateProductRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(productService.createProduct(dto),"Product created Sucessfully"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("product delted successfully");
    }
   
    @PutMapping("edit/{id}")
    public ResponseEntity<ApiResponse<EditProductDto>> putMethodName(@PathVariable("id") Long id, @RequestBody EditProductDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(productService.editProduct(id, dto), "Product Updated sucessfully"));
    }

}
