package com.example.electronicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electronicstore.entity.BasketProducts;
import com.example.electronicstore.entity.BasketProductsId;

public interface BasketProductsRepository extends JpaRepository<BasketProducts, BasketProductsId> {

    List<BasketProducts> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);    
}
