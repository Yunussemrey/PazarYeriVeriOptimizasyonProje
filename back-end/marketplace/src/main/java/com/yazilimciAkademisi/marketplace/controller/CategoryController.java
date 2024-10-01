package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.CategoryRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.CategoryResponseDTO;
import com.yazilimciAkademisi.marketplace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Admin: Create a new category
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO createdCategory = categoryService.createCategory(requestDTO);
        return ResponseEntity.status(201).body(createdCategory);
    }

    // Admin: Update an existing category
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(categoryId, requestDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    // Admin: Delete a category
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    // Public: Get a category by its ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Integer categoryId) {
        CategoryResponseDTO categoryResponse = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryResponse);
    }

    // Public: Get all available categories
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
