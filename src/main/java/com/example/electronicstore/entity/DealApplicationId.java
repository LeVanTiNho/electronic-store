package com.example.electronicstore.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class DealApplicationId implements Serializable{
    private Long dealId;
    private Long productId;
}
