package com.yazilimciAkademisi.marketplace.repository;

import com.yazilimciAkademisi.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query to find a user by email for authentication purposes
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
