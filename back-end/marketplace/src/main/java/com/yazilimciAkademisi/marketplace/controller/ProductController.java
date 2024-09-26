package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;
import com.yazilimciAkademisi.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Store Owner: Create a new product
    @PreAuthorize("hasAuthority('STORE_OWNER')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(requestDTO);
        return ResponseEntity.status(201).body(createdProduct);
    }

    // Store Owner: Update an existing product
    @PreAuthorize("hasAuthority('STORE_OWNER')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Integer productId,
            @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(productId, requestDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    // Authenticated users: Get a product by its ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer productId) {
        ProductResponseDTO productResponse = productService.getProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    // Authenticated users: Get all products of a store
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByStore(@PathVariable Integer storeId) {
        List<ProductResponseDTO> products = productService.getProductsByStore(storeId);
        return ResponseEntity.ok(products);
    }

    // Authenticated users: Get all products
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Store Owner: Delete a product by its ID
    @PreAuthorize("hasAuthority('STORE_OWNER')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}

