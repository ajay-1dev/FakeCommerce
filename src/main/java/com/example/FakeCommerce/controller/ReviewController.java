package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.ReviewDto;
import com.example.FakeCommerce.schema.Review;
import com.example.FakeCommerce.services.ReviewService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping()
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("")
    public Review createReview(@RequestBody ReviewDto entity) {    
        return reviewService.createReview(entity);
    }

    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable("id") Long id){
        reviewService.deleteReview(id);
        System.out.println("Review Deleted Sucessfully");
    }
    
    
}
