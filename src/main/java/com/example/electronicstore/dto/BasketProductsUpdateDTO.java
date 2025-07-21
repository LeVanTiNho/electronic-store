package com.example.electronicstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasketProductsUpdateDTO {
    private Long productId;
    private Integer change;
}