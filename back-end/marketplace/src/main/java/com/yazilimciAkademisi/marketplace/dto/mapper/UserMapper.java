package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.request.UserUpdateRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.SimpleUserResponseDTO;
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
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.USER); // Every new account has standard USER role.
        return newUser;
    }

    public UserResponseDTO toResponseDTO(User appUser) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(appUser.getId());
        dto.setFirstName(appUser.getFirstName());
        dto.setLastName(appUser.getLastName());
        dto.setEmail(appUser.getEmail());
        dto.setRole(appUser.getRole());
        if (appUser.getStore() != null) {
            dto.setStore(storeMapper.toResponseDTO(appUser.getStore()));
        }
        return dto;
    }

    public SimpleUserResponseDTO toSimpleResponseDTO(User user) {
        SimpleUserResponseDTO dto = new SimpleUserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public List<UserResponseDTO> toUserResponseDTOList(List<User> appUserList) {
        return appUserList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<SimpleUserResponseDTO> toSimpleUserResponseDTOList(List<User> appUserList) {
        return appUserList.stream()
                .map(this::toSimpleResponseDTO)
                .collect(Collectors.toList());
    }

    public void updateUserFromDTO(UserUpdateRequestDTO dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
    }
}
