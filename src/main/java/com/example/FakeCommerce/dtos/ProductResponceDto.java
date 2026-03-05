package com.example.FakeCommerce.dtos;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponceDto {

    private String title;

    private String description;

    private BigDecimal price;

    private String image;

    private BigDecimal rating;
}
