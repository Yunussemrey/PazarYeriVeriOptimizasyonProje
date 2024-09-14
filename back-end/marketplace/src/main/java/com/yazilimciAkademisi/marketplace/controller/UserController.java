package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> gelAllUsers() {
        List<UserResponseDTO> users = userService.getAllUserDTOs();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO userResponseDTO = userService.getUserResponseDTOById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userUpdateRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
