package com.example.electricstore.service;

import org.springframework.stereotype.Service;
import com.example.electricstore.entity.Deal;
import com.example.electricstore.repository.DealRepository;

import java.util.List;

@Service
public class DealService {
    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElseThrow(() -> new RuntimeException("Deal not found"));
    }

    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public Deal updateDeal(Long id, Deal dealDetails) {
        Deal deal = getDealById(id);
        deal.setProductId(dealDetails.getProductId());
        deal.setName(dealDetails.getName());
        deal.setDiscount(dealDetails.getDiscount());
        deal.setDescription(dealDetails.getDescription());
        return dealRepository.save(deal);
    }

    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }
}
