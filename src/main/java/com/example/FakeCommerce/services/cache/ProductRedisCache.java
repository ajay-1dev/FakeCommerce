package com.example.FakeCommerce.services.cache;

import java.time.Duration;
import java.util.Optional;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.ProductDetailsResponceIdDto;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRedisCache {
    
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    private static final String KEY_SUMMARY = "product:summary:";
    private final Duration CACHE_TTL = Duration.ofMinutes(1);

    public Optional<ProductDetailsResponceIdDto> getSummary(Long id){
        String responseJson = stringRedisTemplate.opsForValue().get(KEY_SUMMARY + id);

       if(responseJson == null){
        log.info("Cache miss for the product summary");
        return Optional.empty();
       }


       try{
        ProductDetailsResponceIdDto response = objectMapper.readValue(responseJson, ProductDetailsResponceIdDto.class);
        log.info("Cache hit for product");
        return Optional.of(response);
       }
       catch(Exception e){
        log.error("Error parsing the product summary from cache : {} "+ e.getMessage());
        return Optional.empty();
       }
    }

    public void putSummary(Long id,ProductDetailsResponceIdDto response){
        try{
            stringRedisTemplate.opsForValue().set(
                KEY_SUMMARY + id,
                objectMapper.writeValueAsString(response),
                CACHE_TTL);
        }
        catch(Exception e){
            throw new RuntimeException("Error Serializing product summary to cache" + e.getMessage());
        }
    }

}
