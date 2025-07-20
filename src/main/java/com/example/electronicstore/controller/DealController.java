package com.example.electronicstore.controller;

import org.springframework.web.bind.annotation.*;

import com.example.electronicstore.dto.DealDTO;
import com.example.electronicstore.entity.Deal;
import com.example.electronicstore.service.DealService;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public List<DealDTO> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("/{id}")
    public DealDTO getDealById(@PathVariable Long id) {
        return dealService.getDealById(id);
    }

    @PostMapping
    public DealDTO createDeal(@RequestBody Deal deal) {
        return dealService.saveDeal(deal);
    }

    @PutMapping("/{id}")
    public DealDTO updateDeal(@PathVariable Long id, @RequestBody DealDTO updateDealDTO) {
        return dealService.updateDeal(id, updateDealDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return "Deal deleted successfully";
    }
}
