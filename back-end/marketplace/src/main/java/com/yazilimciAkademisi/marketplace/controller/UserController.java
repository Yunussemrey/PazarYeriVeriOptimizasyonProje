package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;
import com.yazilimciAkademisi.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Authenticated users: Get the current logged-in user
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {
        UserResponseDTO currentUser = userService.getUserById(userService.getCurrentUser().getId());
        return ResponseEntity.ok(currentUser);
    }

    // Admin: Get a user by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer userId) {
        UserResponseDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Admin: Get all users
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Authenticated user: Update the currently logged-in user
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateCurrentUser(@RequestBody UserUpdateRequestDTO requestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userService.getCurrentUser().getId(), requestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Admin or self: Delete a user
    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // Assign the admin role to a user (Only SUPER_ADMIN can do this)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PutMapping("/{userId}/assign-admin")
    public ResponseEntity<Void> assignAdminRole(@PathVariable Integer userId) {
        userService.updateUserRole(userId, Role.ADMIN);
        return ResponseEntity.ok().build();
    }

    // Assign the admin role to a user (Only SUPER_ADMIN can do this)
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PutMapping("/{userId}/remove-admin")
    public ResponseEntity<Void> removeAdminRole(@PathVariable Integer userId) {
        userService.updateUserRole(userId, Role.USER);
        return ResponseEntity.ok().build();
    }
    

}

