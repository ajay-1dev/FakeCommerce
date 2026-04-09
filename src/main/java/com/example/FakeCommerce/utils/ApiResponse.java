package com.example.FakeCommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private String error;
    private T body;

    public static <T> ApiResponse<T> success(T data, String message){
        return ApiResponse.
        <T>builder()
        .message(message)
        .body(data)
        .success(true)
        .build();
    }

    public static <T> ApiResponse<T> error(String error,String message){
        return ApiResponse.<T>builder()
        .success(false)
        .message(message)
        .error(error)
        .build();
    }

}
