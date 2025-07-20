package com.example.electronicstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.electronicstore.entity.DealApplication;
import com.example.electronicstore.entity.DealApplicationId;
import com.example.electronicstore.entity.Product;
import com.example.electronicstore.dto.ProductDTO;
import com.example.electronicstore.mapper.ProductMapper;
import com.example.electronicstore.repository.DealApplicationRepository;
import com.example.electronicstore.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DealApplicationRepository dealApplicationRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, 
                          DealApplicationRepository dealApplicationRepository) {
        this.productRepository = productRepository;
        this.dealApplicationRepository = dealApplicationRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productMapper.toDTOList(productRepository.findAll());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDTO(product);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product saved = productRepository.save(product);
        return productMapper.toDTO(saved);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        Boolean isUpdateRequired = false;
        if (productDTO.getName() != null) {
            currentProduct.setName(productDTO.getName());
            isUpdateRequired = true;
        }
        if (productDTO.getCategory() != null) {
            currentProduct.setCategory(productDTO.getCategory());
            isUpdateRequired = true;
        }
        if (productDTO.getPrice() != null) {
            currentProduct.setPrice(productDTO.getPrice());
            isUpdateRequired = true;
        }
        if (productDTO.getQuantity() != null) {
            currentProduct.setQuantity(productDTO.getQuantity());
            isUpdateRequired = true;
        }
        if (isUpdateRequired) {
            Product updated = productRepository.save(currentProduct);
            return productMapper.toDTO(updated);
        } else {
            return productMapper.toDTO(currentProduct);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addDealsToProduct(Long productId, List<Long> dealIds) {
        // todo: verify if product exists, deal exists, and add deals to product

        List<DealApplication> dealApplications = dealIds.stream()
            .map(dealId -> {
                DealApplicationId dealApplicationId = new DealApplicationId();
                dealApplicationId.setProductId(productId);
                dealApplicationId.setDealId(dealId);
                DealApplication dealApplication = new DealApplication();
                return dealApplication;
            })
            .toList();
      
        dealApplicationRepository.saveAll(dealApplications);
    }
}
