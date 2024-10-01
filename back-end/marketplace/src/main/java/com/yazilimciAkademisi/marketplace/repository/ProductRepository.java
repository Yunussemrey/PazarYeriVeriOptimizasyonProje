package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Find products by store
    List<Product> findByStoreId(Integer storeId);
    // Find products by category
    List<Product> findByCategoryId(Integer categoryId);
    // Find products by brand
    List<Product> findByBrandId(Integer brandId);
}
