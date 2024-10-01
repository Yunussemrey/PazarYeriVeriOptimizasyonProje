package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    // To find a store by its associated user
    Optional<Store> findByUserId(Integer userId);
}
