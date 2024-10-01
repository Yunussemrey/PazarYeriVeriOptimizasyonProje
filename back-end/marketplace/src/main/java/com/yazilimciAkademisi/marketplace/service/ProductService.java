package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.request.ProductRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
    ProductResponseDTO updateProduct(Integer productId, ProductRequestDTO requestDTO);
    void deleteProduct(Integer productId);
    ProductResponseDTO getProductById(Integer productId);
    List<ProductResponseDTO> getProductsByStore(Integer storeId);
    List<ProductResponseDTO> getAllProducts();
}
