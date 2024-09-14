package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.UserMapper;
import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.exception.UserNotFoundException;
import com.yazilimciAkademisi.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseDTOList(users);
    }

    public UserResponseDTO getUserResponseDTOById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist."));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO updateUser(Integer id, UserUpdateRequestDTO userUpdateRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist."));
        // TODO: control and update only given parameters
        if (userUpdateRequestDTO.getFirstName() != null) {
            existingUser.setFirstName(userUpdateRequestDTO.getFirstName());
        }
        if (userUpdateRequestDTO.getLastName() != null) {
            existingUser.setLastName(userUpdateRequestDTO.getLastName());
        }
        if (userUpdateRequestDTO.getEmail() != null) {
            existingUser.setEmail(userUpdateRequestDTO.getEmail());
        }
        User updatedAppUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedAppUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}
