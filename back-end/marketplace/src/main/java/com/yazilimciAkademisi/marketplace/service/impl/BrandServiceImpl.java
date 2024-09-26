package com.yazilimciAkademisi.marketplace.service.impl;

import com.yazilimciAkademisi.marketplace.dto.mapper.BrandMapper;
import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.exception.BrandNotFoundException;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import com.yazilimciAkademisi.marketplace.repository.ProductRepository;
import com.yazilimciAkademisi.marketplace.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ProductRepository productRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.brandMapper = brandMapper;
    }

    // Admin: Create a new brand
    @Transactional
    @Override
    public BrandResponseDTO createBrand(BrandRequestDTO requestDTO) {
        Brand brand = brandMapper.toEntity(requestDTO);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toResponseDTO(savedBrand);
    }

    // Admin: Update an existing brand
    @Transactional
    @Override
    public BrandResponseDTO updateBrand(Integer brandId, BrandRequestDTO requestDTO) {
        Brand existingBrand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));

        // Use mapper to update brand fields
        brandMapper.updateBrandFromDTO(requestDTO, existingBrand);

        Brand updatedBrand = brandRepository.save(existingBrand);
        return brandMapper.toResponseDTO(updatedBrand);
    }

    // Admin: Delete a brand (check if associated with products)
    @Transactional
    @Override
    public void deleteBrand(Integer brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));

        // Check if any products are associated with this brand
        boolean hasProducts = !productRepository.findByBrandId(brandId).isEmpty();
        if (hasProducts) {
            throw new IllegalStateException("Cannot delete brand as it is associated with products");
        }

        brandRepository.deleteById(brandId);
    }

    // Public: Get a brand by its ID
    @Override
    public BrandResponseDTO getBrandById(Integer brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        return brandMapper.toResponseDTO(brand);
    }

    // Public: Get all available brands
    @Override
    public List<BrandResponseDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toBrandResponseDTOList(brands);
    }
}
