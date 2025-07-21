package com.example.electronicstore.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class BasketProductsId implements Serializable{
    private Long userId;
    private Long productId;
}
