package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findByName(String name);
}
