package com.yazilimciAkademisi.marketplace.repository;


import com.yazilimciAkademisi.marketplace.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

}
