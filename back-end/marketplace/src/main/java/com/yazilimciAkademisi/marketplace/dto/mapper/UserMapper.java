package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.UserRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.UserResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.AppUser;
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


    public AppUser toEntity(UserRequestDTO dto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(dto.getUsername());
        appUser.setPassword(dto.getPassword());
        appUser.setEmail(dto.getEmail());
        appUser.setRole(Role.STOREUSER);
        return appUser;
    }

    public UserResponseDTO toResponseDTO(AppUser appUser) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(appUser.getId());
        dto.setUsername(appUser.getUsername());
        dto.setPassword(appUser.getPassword());
        dto.setEmail(appUser.getEmail());
        dto.setRole(appUser.getRole());
        if (dto.getStore() != null) {
            dto.setStore(storeMapper.toResponseDTO(appUser.getStore()));
        }
        return dto;
    }

    public List<UserResponseDTO> toUserResponseDTOList(List<AppUser> appUserList) {
        return appUserList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
