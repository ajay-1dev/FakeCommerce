package com.example.FakeCommerce.services;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateProductRequestDto;
import com.example.FakeCommerce.dtos.ProductDetailsResponceIdDto;
import com.example.FakeCommerce.dtos.ProductResponceDto;
import com.example.FakeCommerce.repositiories.ProductRepository;
import com.example.FakeCommerce.schema.Category;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDetailsResponceIdDto getProductDtoById(Long id){
        Product ref = productRepository.getProductByIds(id).get(0);
        ProductDetailsResponceIdDto ans = ProductDetailsResponceIdDto.builder()
        .Category_name(ref.getCategory().getName())
        .description(ref.getDescription())
        .image(ref.getImage())
        .price(ref.getPrice())
        .rating(ref.getRating())
        .title(ref.getRating())
        .build();
        return ans;

    }

    public Product createProduct(CreateProductRequestDto dto){
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        Product  newpProduct = Product.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .image(dto.getImage())
        .price(dto.getPrice())
        .category(category)
        .rating(dto.getRating())
        .build();

        return productRepository.save(newpProduct);  // this will save the product to the database

    }

    public void delteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCatergory(String category){
        return productRepository.findByCategory(category);
    }

    public List<String> getAllCategory(){
        return productRepository.findAllCategories();
    }

    public List<ProductResponceDto> getProductDto(){

        List<ProductResponceDto> res = new ArrayList<>();
        List<Product> product = productRepository.findAll();

    for (Product products : product) {
        ProductResponceDto dto = ProductResponceDto.builder()
                .price(products.getPrice())
                .rating(products.getRating())
                .description(products.getDescription())
                .title(products.getTitle())
                .image(products.getImage())
                .build();

                res.add(dto);
            }
        return res;
    }

    public List<Product> getProducts(){
        return productRepository.getAllProducts();
    }
}
