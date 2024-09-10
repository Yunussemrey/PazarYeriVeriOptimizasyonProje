package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.AppUser;
import com.yazilimciAkademisi.marketplace.exception.AppUserNotFoundException;
import com.yazilimciAkademisi.marketplace.exception.StoreNotFoundException;
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
        List<AppUser> appUsers = userRepository.findAll();
        return userMapper.toUserResponseDTOList(appUsers);
    }

    public UserResponseDTO getUserResponseDTOById(Integer id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException("User with ID " + id + " does not exist."));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        AppUser newAppUser = userMapper.toEntity(userRequestDTO);
        AppUser savedAppUser = userRepository.save(newAppUser);
        return userMapper.toResponseDTO(savedAppUser);
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        AppUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException("User with ID " + id + " does not exist."));
        existingUser.setUsername(userRequestDTO.getUsername());
        existingUser.setPassword(userRequestDTO.getPassword());
        existingUser.setEmail(userRequestDTO.getEmail());
        AppUser updatedAppUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedAppUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new AppUserNotFoundException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}
