package com.example.electronicstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasketProductsUpdateItemDTO {
    private Long productId;
    private Integer change;
}
