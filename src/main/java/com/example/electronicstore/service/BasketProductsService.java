package com.example.electronicstore.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.electronicstore.dto.BasketProductsUpdateDTO;
import com.example.electronicstore.entity.BasketProducts;
import com.example.electronicstore.entity.BasketProductsId;
import com.example.electronicstore.entity.Product;
import com.example.electronicstore.repository.BasketProductsRepository;
import com.example.electronicstore.repository.ProductRepository;





@Service
public class BasketProductsService {
    private final BasketProductsRepository basketProductsRepository;
    private final ProductRepository productRepository;

    public BasketProductsService(BasketProductsRepository basketProductsRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.basketProductsRepository = basketProductsRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateBasketProducts(Long userId, BasketProductsUpdateDTO basketProductsUpdateDTO) {  
        Optional<Product> productFindResult = productRepository.findById(basketProductsUpdateDTO.getProductId());
        
        if (productFindResult.isPresent()) {
            throw new IllegalArgumentException("Product not found with ID: " + basketProductsUpdateDTO.getProductId());
        }

        Product product = productFindResult.get();
    
        if (basketProductsUpdateDTO.getChange() > 0) {
            if (product.getQuantity() < basketProductsUpdateDTO.getChange()) {
                throw new IllegalArgumentException("Not enough product quantity available");
            }

            product.setQuantity(product.getQuantity() - basketProductsUpdateDTO.getChange());
            productRepository.save(product);

            // Add product to basket
            BasketProductsId basketProductsId = new BasketProductsId(userId, product.getId());
            Optional<BasketProducts> basketProduct = basketProductsRepository.findById(basketProductsId);
            if (basketProduct.isPresent()) {
                BasketProducts existingBasketProduct = basketProduct.get();
                existingBasketProduct.setQuantity(existingBasketProduct.getQuantity() + basketProductsUpdateDTO.getChange());
                basketProductsRepository.save(existingBasketProduct);
            } else {
                BasketProducts newBasketProduct = new BasketProducts(basketProductsId, basketProductsUpdateDTO.getChange());
                basketProductsRepository.save(newBasketProduct);
            }
        } else if (basketProductsUpdateDTO.getChange() < 0) {
            // Remove product from basket
            BasketProductsId basketProductsId = new BasketProductsId(userId, product.getId());
            Optional<BasketProducts> basketProduct = basketProductsRepository.findById(basketProductsId);
            if (basketProduct.isPresent()) {
                BasketProducts existingBasketProduct = basketProduct.get();
                int newQuantity = existingBasketProduct.getQuantity() + basketProductsUpdateDTO.getChange();
                if (newQuantity <= 0) {
                    basketProductsRepository.delete(existingBasketProduct);
                    product.setQuantity(product.getQuantity() + existingBasketProduct.getQuantity());
                    productRepository.save(product);
                } else {
                    existingBasketProduct.setQuantity(newQuantity);
                    basketProductsRepository.save(existingBasketProduct);
                    product.setQuantity(product.getQuantity() + basketProductsUpdateDTO.getChange());
                    productRepository.save(product);
                }
            } else {
                throw new IllegalArgumentException("Product not found in basket with ID: " + product.getId());
            }
        } else {
            throw new IllegalArgumentException("Change quantity must be greater than 0 or less than 0");
        }
    }
}

