package com.example.electronicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicstore.entity.OrderProducts;
import com.example.electronicstore.entity.OrderProductsId;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, OrderProductsId> {
}
