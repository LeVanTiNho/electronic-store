package com.example.electronicstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer quantity;
}
