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
public class OrderProductsId implements Serializable{
    private Long orderId;
    private Long productId;
}
