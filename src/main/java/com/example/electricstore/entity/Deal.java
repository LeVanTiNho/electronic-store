package com.example.electricstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private String description;
}
