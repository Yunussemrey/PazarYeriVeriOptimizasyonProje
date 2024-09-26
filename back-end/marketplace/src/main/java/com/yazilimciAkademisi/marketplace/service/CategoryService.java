package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO);
    CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO requestDTO);
    void deleteCategory(Integer id);
    CategoryResponseDTO getCategoryById(Integer id);
    List<CategoryResponseDTO> getAllCategories();
}
