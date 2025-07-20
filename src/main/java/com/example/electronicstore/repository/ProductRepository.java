package com.example.electronicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electronicstore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface will automatically inherit methods for CRUD operations
    // on Product entities, such as save, findById, findAll, deleteById, etc.
    
}
