package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;
import com.yazilimciAkademisi.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOS= productService.getAllProductDTOs();
        return ResponseEntity.ok(productResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id) {
        ProductResponseDTO productResponseDTO = productService.getProductResponseDTOById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.saveProduct(productRequestDTO);
        return ResponseEntity.status(201).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
