package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    // Admin: Create a new brand
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandRequestDTO requestDTO) {
        BrandResponseDTO createdBrand = brandService.createBrand(requestDTO);
        return ResponseEntity.status(201).body(createdBrand);
    }

    // Admin: Update an existing brand
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> updateBrand(
            @PathVariable Integer brandId,
            @RequestBody BrandRequestDTO requestDTO) {
        BrandResponseDTO updatedBrand = brandService.updateBrand(brandId, requestDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    // Admin: Delete a brand
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }

    // Public: Get a brand by its ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Integer brandId) {
        BrandResponseDTO brand = brandService.getBrandById(brandId);
        return ResponseEntity.ok(brand);
    }

    // Public: Get all available brands
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands() {
        List<BrandResponseDTO> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

}
