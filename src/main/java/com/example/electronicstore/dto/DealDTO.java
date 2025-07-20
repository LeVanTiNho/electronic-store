package com.example.electronicstore.dto;

import lombok.Data;

@Data
public class DealDTO {
    private Long id;
    private String name;
    private Double discount;
    private String description;
}
