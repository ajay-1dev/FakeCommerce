package com.example.FakeCommerce.dtos;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateProductRequestDto {
    private String title;

    private String description;

    private BigDecimal price;

    private String image;

    private Long categoryId;

    private String rating;
}
