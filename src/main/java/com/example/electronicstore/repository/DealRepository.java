package com.example.electronicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicstore.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
    // Inherits CRUD methods for Deal entities
}
