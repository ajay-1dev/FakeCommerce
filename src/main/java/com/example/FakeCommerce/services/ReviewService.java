package com.example.FakeCommerce.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.FakeCommerce.adapters.ReviewAdapter;
import com.example.FakeCommerce.dtos.GetReviewResponseDTO;
import com.example.FakeCommerce.dtos.ReviewDto;
import com.example.FakeCommerce.exeptions.ResourceNotFoundException;
import com.example.FakeCommerce.repositiories.OrderProductsReposistory;
import com.example.FakeCommerce.repositiories.OrderRepository;
import com.example.FakeCommerce.repositiories.ProductRepository;
import com.example.FakeCommerce.repositiories.ReviewRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProducts;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.schema.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewAdapter reviewAdapter;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductsReposistory orderProductsReposistory;

    public List<GetReviewResponseDTO> getAllReviews() {
        return reviewAdapter.mapToGetReviewResponseDtoList(
                reviewRepository.findAll()
        );
    }

    public GetReviewResponseDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewAdapter::mapToGetReviewResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
    }

    public List<GetReviewResponseDTO> getReviewsByProductId(Long productId) {
        return reviewAdapter.mapToGetReviewResponseDtoList(
                reviewRepository.findByProductId(productId)
        );
    }

    public List<GetReviewResponseDTO> getReviewsByOrderId(Long orderId) {
        return reviewAdapter.mapToGetReviewResponseDtoList(
                reviewRepository.findByOrderId(orderId)
        );
    }

    public Review createReview(ReviewDto review) {
        List<OrderProducts> orderProducts = orderProductsReposistory.findByOrderId(review.getOrderId());
        Set<Long> pid = orderProducts.stream().map(op -> op.getProduct().getId()).collect(Collectors.toSet());

        Set<Long> oid = orderProducts.stream().map(op -> op.getOrder().getId()).collect(Collectors.toSet());
        if(!oid.contains(review.getOrderId()) || !pid.contains(review.getProductId())){
            throw new ResourceNotFoundException("Unable to write reiew on this");
        }
        Order order = orderRepository.findById(review.getOrderId())
        .orElseThrow(()  -> new ResourceNotFoundException("Unable to write reiew on this"));
        Product product = productRepository.findById(review.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException("Unable to write reiew on this"));
        Review review2 = Review.builder()
        .comment(review.getComment())
        .product(product)
        .order(order)
        .rating(review.getRating())
        .build();
        
        return reviewRepository.save(review2);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));

        reviewRepository.delete(review);
    }
}