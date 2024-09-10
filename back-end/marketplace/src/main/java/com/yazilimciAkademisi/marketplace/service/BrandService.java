package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.BrandMapper;
import com.yazilimciAkademisi.marketplace.dto.request.BrandRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.BrandResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.exception.BrandNotFoundException;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public List<BrandResponseDTO> getAllBrandDTOs() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toBrandResponseDTOList(brands);
    }

    public BrandResponseDTO getBrandResponseDTOById(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand with ID " + id + " not found."));
        return brandMapper.toResponseDTO(brand);
    }

    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    public BrandResponseDTO saveBrand(BrandRequestDTO brandRequestDTO) {
        Brand brand = brandMapper.toEntity(brandRequestDTO);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toResponseDTO(savedBrand);
    }

    public BrandResponseDTO updateBrand(Integer id, BrandRequestDTO brandRequestDTO) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand with ID " + id + " not found."));
        existingBrand.setName(brandRequestDTO.getName());
        Brand updatedBrand = brandRepository.save(existingBrand);
        return brandMapper.toResponseDTO(updatedBrand);
    }

    public void deleteBrand(Integer id) {
        if (!brandRepository.existsById(id)) {
            throw new BrandNotFoundException("Brand with ID " + id + " does not exist.");
        }
        brandRepository.deleteById(id);
    }
}
