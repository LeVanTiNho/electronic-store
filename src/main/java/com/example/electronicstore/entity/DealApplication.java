package com.example.electronicstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deal_application")
@Getter
@Setter
public class DealApplication {
   @EmbeddedId
    private DealApplicationId id;
}
