package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.AppUser;
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
        Optional<AppUser> userOptional = userRepository.findById(id);
        return userOptional.map(userMapper::toResponseDTO);
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        AppUser newAppUser = userMapper.toEntity(userRequestDTO);
        AppUser savedAppUser = userRepository.save(newAppUser);
        return userMapper.toResponseDTO(savedAppUser);
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        Optional<AppUser> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            AppUser existingAppUser = existingUserOptional.get();
            existingAppUser.setUsername(userRequestDTO.getUsername());
            existingAppUser.setPassword(userRequestDTO.getPassword());
            existingAppUser.setEmail(userRequestDTO.getEmail());
            AppUser updatedAppUser = userRepository.save(existingAppUser);
            return userMapper.toResponseDTO(updatedAppUser);
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
