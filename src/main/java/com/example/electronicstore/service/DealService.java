package com.example.electronicstore.service;

import org.springframework.stereotype.Service;

import com.example.electronicstore.dto.DealDTO;
import com.example.electronicstore.dto.UpdateDealDTO;
import com.example.electronicstore.entity.Deal;
import com.example.electronicstore.mapper.DealMapper;
import com.example.electronicstore.repository.DealRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    public DealService(DealRepository dealRepository, DealMapper dealMapper) {
        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
    }

    public List<DealDTO> getAllDeals() {
        return dealMapper.toDTOList(dealRepository.findAll());
    }

    public DealDTO getDealById(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new RuntimeException("Deal not found"));
        return dealMapper.toDTO(deal);
    }

    public DealDTO saveDeal(Deal deal) {
        Deal created = dealRepository.save(deal);
        return dealMapper.toDTO(created);
    }

    public DealDTO updateDeal(Long id, UpdateDealDTO updateDealDTO) {
        Optional<Deal> currentDealFindResult = dealRepository.findById(id);
        if (!currentDealFindResult.isPresent()) {
            throw new RuntimeException("Deal not found");
        }
        Deal currentDeal = currentDealFindResult.get();
        currentDeal.setProductId(updateDealDTO.getProductId());
        currentDeal.setName(updateDealDTO.getName());
        currentDeal.setDiscount(updateDealDTO.getDiscount());
        currentDeal.setDescription(updateDealDTO.getDescription());
        Deal updatedDeal = dealRepository.save(currentDeal);

        return dealMapper.toDTO(updatedDeal);
    }

    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }
}
