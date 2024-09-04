package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUserDTOs() {
        return userMapper.toUserResponseDTOList(userRepository.findAll());
    }

    public Optional<UserResponseDTO> getUserResponseDTOById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toResponseDTO);
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        User newUser = userMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(newUser);
        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(userRequestDTO.getUsername());
            existingUser.setPassword(userRequestDTO.getPassword());
            existingUser.setEmail(userRequestDTO.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toResponseDTO(updatedUser);
        } else {
            throw new IllegalArgumentException("Store with ID " + id + " does not exist.");
        }
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}
