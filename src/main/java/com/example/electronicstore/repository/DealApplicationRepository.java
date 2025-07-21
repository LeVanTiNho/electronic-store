package com.example.electronicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electronicstore.entity.DealApplication;
import com.example.electronicstore.entity.DealApplicationId;

public interface DealApplicationRepository extends JpaRepository<DealApplication, DealApplicationId> {
}
