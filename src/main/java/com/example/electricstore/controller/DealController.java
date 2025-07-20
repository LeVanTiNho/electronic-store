package com.example.electricstore.controller;

import org.springframework.web.bind.annotation.*;
import com.example.electricstore.entity.Deal;
import com.example.electricstore.service.DealService;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public List<Deal> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("/{id}")
    public Deal getDealById(@PathVariable Long id) {
        return dealService.getDealById(id);
    }

    @PostMapping
    public Deal createDeal(@RequestBody Deal deal) {
        return dealService.saveDeal(deal);
    }

    @PutMapping("/{id}")
    public Deal updateDeal(@PathVariable Long id, @RequestBody Deal deal) {
        return dealService.updateDeal(id, deal);
    }

    @DeleteMapping("/{id}")
    public String deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return "Deal deleted successfully";
    }
}
