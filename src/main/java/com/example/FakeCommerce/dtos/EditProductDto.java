package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProductDto extends ProductResponceDetailsDto {
    private Long Id;
}
