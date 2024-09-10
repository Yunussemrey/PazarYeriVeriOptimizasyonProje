package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.CategoryMapper;
import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.exception.CategoryNotFoundException;
import com.yazilimciAkademisi.marketplace.exception.SelfParentCategoryException;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDTO> getAllCategoryDTOs() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponseDTOList(categories);

    }

    public CategoryResponseDTO getCategoryResponseDTOById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found."));
        return categoryMapper.toResponseDTO(category);
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        updateParentCategory(category, categoryRequestDTO.getParentCategoryId());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(savedCategory);
    }

    // Update Category
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " does not exist."));
        // Update the existing category properties
        existingCategory.setName(categoryRequestDTO.getName());
        // Update parent category
        updateParentCategory(existingCategory, categoryRequestDTO.getParentCategoryId());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponseDTO(updatedCategory);
    }

    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }

    // Update Parent Category
    private void updateParentCategory(Category category, Integer newParentCategoryId) {
        // Category cannot be its own parent
        if (newParentCategoryId != null && newParentCategoryId.equals(category.getId())) {
            throw new SelfParentCategoryException("A category cannot be its own parent.");
        }
        // Remove from the old parent category's subcategories
        if (category.getParentCategory() != null) {
            Category oldParentCategory = category.getParentCategory();
            oldParentCategory.getSubCategories().remove(category);
        }
        // Update new parent category
        if (newParentCategoryId != null) {
            Optional<Category> newParentCategoryOptional = getCategoryById(newParentCategoryId);
            if (newParentCategoryOptional.isPresent()) {
                Category newParentCategory = newParentCategoryOptional.get();
                category.setParentCategory(newParentCategory);
                newParentCategory.getSubCategories().add(category);
            } else {
                category.setParentCategory(null);
            }
        } else {
            category.setParentCategory(null);
        }
    }

}
