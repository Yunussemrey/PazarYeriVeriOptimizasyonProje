package com.yazilimciAkademisi.marketplace.dto.mapper;

import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public Store toEntity(StoreRequestDTO dto, User owner) {
        Store store = new Store();
        store.setName(dto.getName());
        store.setDescription(dto.getDescription());
        store.setPhone(dto.getPhone());
        store.setAddress(dto.getAddress());
        store.setUser(owner);
        return store;
    }

    public StoreResponseDTO toResponseDTO(Store store) {
        StoreResponseDTO dto = new StoreResponseDTO();
        dto.setId(store.getId());
        dto.setName(store.getName());
        dto.setDescription(store.getDescription());
        dto.setPhone(store.getPhone());
        dto.setAddress(store.getAddress());
        dto.setCreatedAt(store.getCreatedAt());
        dto.setUpdatedAt(store.getUpdatedAt());
        return dto;
    }

    public List<StoreResponseDTO> toStoreResponseDTOList(List<Store> storeList) {
        return storeList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
