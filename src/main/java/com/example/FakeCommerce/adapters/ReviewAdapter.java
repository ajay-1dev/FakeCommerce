package com.example.FakeCommerce.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.FakeCommerce.dtos.GetReviewResponseDTO;
import com.example.FakeCommerce.schema.Review;

@Component
public class ReviewAdapter {

    public List<GetReviewResponseDTO> mapToGetReviewResponseDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(this::mapToGetReviewResponseDto)
                .collect(Collectors.toList());
    }

    public GetReviewResponseDTO mapToGetReviewResponseDto(Review review) {
        return GetReviewResponseDTO.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .orderId(review.getOrder().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}