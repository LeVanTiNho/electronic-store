package com.example.electronicstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.electronicstore.dto.BasketProductsUpdateDTO;
import com.example.electronicstore.dto.ProductDTO;
import com.example.electronicstore.service.BasketProductsService;
import com.example.electronicstore.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/basket-products")
public class BasketProductsController {

    private final BasketProductsService basketProductsService;

    private final ProductService productService;

    public BasketProductsController(ProductService productService, BasketProductsService basketProductsService) {
        this.productService = productService;
        this.basketProductsService = basketProductsService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    @PostMapping("")
    public String addProductsToBasket(@RequestBody BasketProductsUpdateDTO basketProductsUpdateDTO) {
        // get the user ID from the session or authentication context
        Long userId = 1L; // This should be replaced with actual user ID retrieval logic
        basketProductsService.updateBasketProducts(userId, basketProductsUpdateDTO);
        return "Deals added to product successfully";
    }
}
