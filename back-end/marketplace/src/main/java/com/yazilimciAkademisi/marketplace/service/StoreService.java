package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;

import java.util.List;

public interface StoreService {
    StoreResponseDTO createStore(StoreRequestDTO requestDTO);
    StoreResponseDTO updateStore(Integer storeId, StoreRequestDTO requestDTO);
    void deleteStore(Integer storeId);
    StoreResponseDTO getStoreById(Integer storeId);
    List<StoreResponseDTO> getAllStores();
}
