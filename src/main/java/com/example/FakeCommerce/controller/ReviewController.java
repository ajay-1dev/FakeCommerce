package com.example.FakeCommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FakeCommerce.dtos.GetReviewResponseDTO;
import com.example.FakeCommerce.dtos.ReviewDto;
import com.example.FakeCommerce.schema.Review;
import com.example.FakeCommerce.services.ReviewService;
import com.example.FakeCommerce.utils.ApiResponse;

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



@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<GetReviewResponseDTO>>> getAllReviews() {
        return ResponseEntity.ok()
        .body(ApiResponse.success(reviewService.getAllReviews(),"Fetched Sucessfully"));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Review>> createReview(@RequestBody ReviewDto entity) {    
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(reviewService.createReview(entity), "Created Sucessfully"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review Deleted Sucessfully");
    }
    
    
}
