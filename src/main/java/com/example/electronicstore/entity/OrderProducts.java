package com.example.electronicstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {
    @EmbeddedId
    private OrderProductsId id;

    @Column(nullable = false)
    private Integer quantity;

    // the price of the product at the time of the order
    @Column(nullable = false)
    private Double price;
}
