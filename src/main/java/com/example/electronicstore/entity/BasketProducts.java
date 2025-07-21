package com.example.electronicstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_basket_products")
@Getter
@Setter
@AllArgsConstructor
public class BasketProducts {
   @EmbeddedId
    private BasketProductsId id;

    @Column(nullable = false)
    private Integer quantity;
}
