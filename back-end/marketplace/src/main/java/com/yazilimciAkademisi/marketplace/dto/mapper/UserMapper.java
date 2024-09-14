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

    public User toEntity(UserRequestDTO dto, String encodedPassword) {
        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setPassword(encodedPassword);
        newUser.setEmail(dto.getEmail());
        newUser.setRole(Role.STOREUSER);
        return newUser;
    }

    public UserResponseDTO toResponseDTO(User appUser) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(appUser.getId());
        dto.setFirstName(appUser.getFirstName());
        dto.setLastName(appUser.getLastName());
        dto.setPassword(appUser.getPassword());
        dto.setEmail(appUser.getEmail());
        dto.setRole(appUser.getRole());
        if (dto.getStore() != null) {
            dto.setStore(storeMapper.toResponseDTO(appUser.getStore()));
        }
        return dto;
    }

    public List<UserResponseDTO> toUserResponseDTOList(List<User> appUserList) {
        return appUserList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
