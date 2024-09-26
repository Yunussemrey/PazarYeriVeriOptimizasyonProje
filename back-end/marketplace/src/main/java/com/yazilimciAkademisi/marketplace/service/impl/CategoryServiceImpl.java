package com.yazilimciAkademisi.marketplace.service.impl;

import com.yazilimciAkademisi.marketplace.dto.mapper.CategoryMapper;
import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.exception.CategoryNotFoundException;
import com.yazilimciAkademisi.marketplace.exception.SelfParentCategoryException;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import com.yazilimciAkademisi.marketplace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    // Admin: Create a new category
    @Transactional
    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = categoryMapper.toEntity(requestDTO);
        updateParentCategory(category, requestDTO.getParentCategoryId());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(savedCategory);
    }

    // Admin: Update an existing category
    @Transactional
    @Override
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO requestDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " does not exist."));
        // Update the existing category properties
        existingCategory.setName(requestDTO.getName());
        // Update parent category
        updateParentCategory(existingCategory, requestDTO.getParentCategoryId());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponseDTO(updatedCategory);
    }

    // Admin: Delete a category
    @Transactional
    @Override
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }

    // Public: Get a category by its ID
    @Override
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found."));
        return categoryMapper.toResponseDTO(category);
    }

    // Public: Get all categories
    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponseDTOList(categories);
    }

    // Update Parent Category with Self-Check
    private void updateParentCategory(Category category, Integer newParentCategoryId) {
        // Ensure a category cannot be its own parent
        if (newParentCategoryId != null && newParentCategoryId.equals(category.getId())) {
            throw new SelfParentCategoryException("A category cannot be its own parent.");
        }

        // Handle old parent category's relationship
        if (category.getParentCategory() != null) {
            Category oldParentCategory = category.getParentCategory();
            oldParentCategory.getSubCategories().remove(category);
        }
        // Handle new parent category relationship
        if (newParentCategoryId != null) {
            Optional<Category> newParentCategoryOptional = categoryRepository.findById(newParentCategoryId);
            if (newParentCategoryOptional.isPresent()) {
                Category newParentCategory = newParentCategoryOptional.get();
                category.setParentCategory(newParentCategory);
                newParentCategory.getSubCategories().add(category);
            } else {
                category.setParentCategory(null);
            }
        } else {
            category.setParentCategory(null); // No parent
        }
    }


}
