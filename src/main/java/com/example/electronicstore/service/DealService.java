package com.example.electronicstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.example.electronicstore.dto.DealDTO;
import com.example.electronicstore.entity.Deal;
import com.example.electronicstore.mapper.DealMapper;
import com.example.electronicstore.repository.DealRepository;

import org.springframework.transaction.annotation.Transactional;


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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DealDTO updateDeal(Long id, DealDTO dealDTO) {
        Optional<Deal> currentDealFindResult = dealRepository.findById(id);
        if (!currentDealFindResult.isPresent()) {
            throw new RuntimeException("Deal not found");
        }
        Deal currentDeal = currentDealFindResult.get();

        Boolean isUpdateRequired = false;

        if (dealDTO.getName() != null) {
            currentDeal.setName(dealDTO.getName());
            isUpdateRequired = true;
        }

        if (dealDTO.getDiscount() != null) {
            currentDeal.setDiscount(dealDTO.getDiscount());
            isUpdateRequired = true;
        }

        if (dealDTO.getDescription() != null) {
            currentDeal.setDescription(dealDTO.getDescription());
            isUpdateRequired = true;
        }

        if (isUpdateRequired) {
            Deal updatedDeal = dealRepository.save(currentDeal);
            return dealMapper.toDTO(updatedDeal);
        } else {
            return dealMapper.toDTO(currentDeal);
        }

    }

    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }
}