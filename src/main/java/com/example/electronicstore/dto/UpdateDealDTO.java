package com.example.electronicstore.dto;

import lombok.Data;

@Data
public class UpdateDealDTO {
    private String productId;
    private String name;
    private Double discount;
    private String description;
}
