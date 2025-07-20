package com.example.electricstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electricstore.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
    // Inherits CRUD methods for Deal entities
}
