package com.example.FakeCommerce.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@MappedSuperclass
public class BaseEntity {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment specially for mysql
    private Long id; //primary key

}
