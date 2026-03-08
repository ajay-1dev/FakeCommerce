package com.example.FakeCommerce.services;

import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.ReviewDto;
import com.example.FakeCommerce.repositiories.ReviewReposistory;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.schema.Review;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@RequiredArgsConstructor
@RequestMapping("api/v1/orderproducts")
public class ReviewService {
    private final ReviewReposistory reviewReposistory;
    private final OrderService orderService;
    private final ProductService productService;

    public List<Review> getAllReviews(){
        return reviewReposistory.findAll();
    }
    
    public Review createReview(ReviewDto review){
        Order order = orderService.getOrderById(review.getOrderId());
        Product product = productService.getProductById(review.getProductId());
            boolean productExists = orderService.getAllOrders()
            .stream()
            .anyMatch(p -> p.getId().equals(product.getId()));

    if(!productExists){
        throw new RuntimeException("This product does not belong to the order");
    }
        Review dummy = Review.builder()
        .rating(review.getRating())
        .order(order)
        .product(product)
        .comment(review.getComment())
        .build();
        return reviewReposistory.save(dummy);
    }

    public void deleteReview(Long id){
        reviewReposistory.deleteById(id);
    }
}
