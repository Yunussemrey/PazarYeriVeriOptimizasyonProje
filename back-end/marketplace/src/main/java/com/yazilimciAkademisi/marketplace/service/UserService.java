package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;

import java.util.List;

public interface UserService {
    User getCurrentUser();
    UserResponseDTO getUserById(Integer userId);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Integer userId, UserUpdateRequestDTO requestDTO);
    void deleteUser(Integer userId);
    void updateUserRole(Integer userId, Role newRole);
}
