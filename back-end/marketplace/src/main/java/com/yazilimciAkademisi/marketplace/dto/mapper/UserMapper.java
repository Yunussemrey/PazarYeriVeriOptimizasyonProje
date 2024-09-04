package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final StoreMapper storeMapper;

    public UserMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }


    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(Role.USER);
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        if (dto.getStore() != null) {
            dto.setStore(storeMapper.toResponseDTO(user.getStore()));
        }
        return dto;
    }

    public List<UserResponseDTO> toUserResponseDTOList(List<User> userList) {
        return userList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
