package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;

import java.util.List;

public interface BrandService {
    BrandResponseDTO createBrand(BrandRequestDTO requestDTO);
    BrandResponseDTO updateBrand(Integer brandId, BrandRequestDTO requestDTO);
    void deleteBrand(Integer brandId);
    BrandResponseDTO getBrandById(Integer brandId);
    List<BrandResponseDTO> getAllBrands();
}
