package com.example.FakeCommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductsDto {
    private Integer quantity;
    private Long order_id;
    private Long product_id;
}
