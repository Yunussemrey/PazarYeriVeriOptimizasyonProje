package com.yazilimciAkademisi.marketplace.service.impl;

import com.yazilimciAkademisi.marketplace.dto.mapper.ProductMapper;
import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Brand;
import com.yazilimciAkademisi.marketplace.entity.Category;
import com.yazilimciAkademisi.marketplace.entity.Product;
import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.exception.*;
import com.yazilimciAkademisi.marketplace.repository.BrandRepository;
import com.yazilimciAkademisi.marketplace.repository.CategoryRepository;
import com.yazilimciAkademisi.marketplace.repository.ProductRepository;
import com.yazilimciAkademisi.marketplace.repository.StoreRepository;
import com.yazilimciAkademisi.marketplace.service.ProductService;
import com.yazilimciAkademisi.marketplace.util.OwnershipUtil;
import com.yazilimciAkademisi.marketplace.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StoreRepository storeRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productMapper = productMapper;
    }

    // Store owner creates a product
    @Transactional
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Store store = storeRepository.findByUserId(userId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));

        OwnershipUtil.checkStoreOwnership(store, userId);

        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Brand brand = brandRepository.findById(requestDTO.getBrandId())
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));

        Product product = productMapper.toEntity(requestDTO, category, brand, store);
        Product savedProduct = productRepository.save(product);

        return productMapper.toResponseDTO(savedProduct);
    }

    // Store owner updates a product
    @Transactional
    @Override
    public ProductResponseDTO updateProduct(Integer productId, ProductRequestDTO requestDTO) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Store store = existingProduct.getStore();
        OwnershipUtil.checkStoreOwnership(store, userId);

        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Brand brand = brandRepository.findById(requestDTO.getBrandId())
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));

        existingProduct.setProductCode(requestDTO.getProductCode());
        existingProduct.setName(requestDTO.getName());
        existingProduct.setDescription(requestDTO.getDescription());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setStockQuantity(requestDTO.getStockQuantity());
        existingProduct.setCategory(category);
        existingProduct.setBrand(brand);

        productRepository.save(existingProduct);
        return productMapper.toResponseDTO(existingProduct);
    }

    // Store owner retrieves a product by its ID
    @Override
    public ProductResponseDTO getProductById(Integer productId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Store store = product.getStore();
        OwnershipUtil.checkStoreOwnership(store, userId);

        return productMapper.toResponseDTO(product);
    }

    // Store owner retrieves all products of a store
    @Override
    public List<ProductResponseDTO> getProductsByStore(Integer storeId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));

        OwnershipUtil.checkStoreOwnership(store, userId);

        List<Product> products = productRepository.findByStoreId(storeId);
        return productMapper.toProductResponseDTOList(products);
    }

    // Retrieve all products (Public)
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductResponseDTOList(products);
    }

    // Store owner deletes a product
    @Transactional
    @Override
    public void deleteProduct(Integer productId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Store store = product.getStore();
        OwnershipUtil.checkStoreOwnership(store, userId);

        productRepository.deleteById(productId);
    }
}
