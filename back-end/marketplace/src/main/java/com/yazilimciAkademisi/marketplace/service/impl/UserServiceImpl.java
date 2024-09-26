package com.yazilimciAkademisi.marketplace.service.impl;

import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;
import com.yazilimciAkademisi.marketplace.exception.UnauthorizedAccessException;
import com.yazilimciAkademisi.marketplace.exception.UserNotFoundException;
import com.yazilimciAkademisi.marketplace.repository.UserRepository;
import com.yazilimciAkademisi.marketplace.service.UserService;
import com.yazilimciAkademisi.marketplace.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Retrieve the currently logged-in user
    @Override
    public User getCurrentUser() {
        Integer currentUserId = SecurityUtil.getCurrentUserId();
        return userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    // Retrieve a user by ID
    @Override
    public UserResponseDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found."));
        return userMapper.toResponseDTO(user);
    }

    // Admin: Get all users
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseDTOList(users);
    }

    // Update user info
    @Transactional
    @Override
    public UserResponseDTO updateUser(Integer userId, UserUpdateRequestDTO requestDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Ensure that only the logged-in user is updating themselves
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(userId)) {
            throw new UnauthorizedAccessException("You do not have permission to update this user.");
        }

        // Use the mapper to update user fields, except password
        userMapper.updateUserFromDTO(requestDTO, existingUser);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    // Delete user by ID
    @Transactional
    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }

        // Ensure that the logged-in user is either deleting themselves or is an admin
        User currentUser = getCurrentUser();
        if (!currentUser.getId().equals(userId) && currentUser.getRole() != Role.ADMIN) {
            throw new UnauthorizedAccessException("You do not have permission to delete this user.");
        }

        userRepository.deleteById(userId);
    }

    // Only SUPER_ADMIN can assign/remove ADMIN roles
    @Transactional
    @Override
    public void updateUserRole(Integer userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));

        Role oldRole = user.getRole();
        User currentUser = getCurrentUser();
        Role currentUserRole = currentUser.getRole();

        // Super admins cannot change their roles
        if (newRole == Role.SUPER_ADMIN ||oldRole == Role.SUPER_ADMIN) {
            throw new UnauthorizedAccessException("Cannot assign this role to anyone.");
        }
        // Handle Admin related role changes
        if ((newRole == Role.ADMIN || oldRole == Role.ADMIN) && currentUserRole == Role.SUPER_ADMIN) {
            handleAdminRoleChange(currentUserRole, oldRole, newRole);
        }
        // If all conditions are passed, update the role
        user.setRole(newRole);
        userRepository.save(user);

    }


    private void handleAdminRoleChange(Role currentUserRole,Role oldRole, Role newRole) {
        // Ensure only SUPER_ADMIN can assign or remove admin roles
        if (currentUserRole != Role.SUPER_ADMIN) {
            throw new UnauthorizedAccessException("Only super admin can handle admin roles.");
        }
        // Store owners can not be assigned to admin
        if (oldRole == Role.STORE_OWNER) {
            throw new UnauthorizedAccessException("Store owners cannot be assigned the admin role.");
        }
    }

}
