package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands() {
        List<BrandResponseDTO> brands = brandService.getAllBrandDTOs();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> getBrandById(@PathVariable Integer id) {
        BrandResponseDTO brandDTO = brandService.getBrandResponseDTOById(id);
        return ResponseEntity.ok(brandDTO);
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> createBrand(@RequestBody BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO createdBrand = brandService.saveBrand(brandRequestDTO);
        return ResponseEntity.status(201).body(createdBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateBrand(@PathVariable Integer id, @RequestBody BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO updatedBrandDTO = brandService.updateBrand(id, brandRequestDTO);
        return ResponseEntity.ok(updatedBrandDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
