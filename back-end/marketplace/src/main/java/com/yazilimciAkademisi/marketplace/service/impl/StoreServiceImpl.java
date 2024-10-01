package com.yazilimciAkademisi.marketplace.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.yazilimciAkademisi.marketplace.dto.mapper.StoreMapper;
import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.entity.User;
import com.yazilimciAkademisi.marketplace.entity.enums.Role;
import com.yazilimciAkademisi.marketplace.exception.StoreNotFoundException;
import com.yazilimciAkademisi.marketplace.exception.UnauthorizedAccessException;
import com.yazilimciAkademisi.marketplace.repository.StoreRepository;
import com.yazilimciAkademisi.marketplace.service.StoreService;
import com.yazilimciAkademisi.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final UserService userService;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper, UserService userService) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.userService = userService;
    }

    // Store Owner: Create a new store
    @Transactional
    @Override
    public StoreResponseDTO createStore(StoreRequestDTO requestDTO) {
        // Get the current logged-in user
        User currentUser = userService.getCurrentUser();
        // Check if the user already owns a store
        if (currentUser.getStore() != null) {
            throw new IllegalStateException("You already have a store.");
        }
        // Create and save the store
        Store store = storeMapper.toEntity(requestDTO, currentUser);
        Store savedStore = storeRepository.save(store);
        // Change user's role to STORE_OWNER
        userService.updateUserRole(currentUser.getId(), Role.STORE_OWNER);
        return storeMapper.toResponseDTO(savedStore);
    }

    // Store Owner: Update their own store
    @Transactional
    @Override
    public StoreResponseDTO updateStore(Integer storeId, StoreRequestDTO requestDTO) {
        // Get the current logged-in user
        User currentUser = userService.getCurrentUser();
        // Find the store and ensure ownership
        Store existingStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store with ID: " + storeId + " not found."));
        if (!existingStore.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("You do not have permission to update this store.");
        }
        // Update the store fields
        existingStore.setName(requestDTO.getName());
        existingStore.setDescription(requestDTO.getDescription());
        existingStore.setPhone(requestDTO.getPhone());
        existingStore.setAddress(requestDTO.getAddress());
        existingStore.setUpdatedAt(LocalDateTime.now());
        Store updatedStore = storeRepository.save(existingStore);
        return storeMapper.toResponseDTO(updatedStore);
    }

    // Store Owner/Admin: Delete a store
    @Transactional
    @Override
    public void deleteStore(Integer storeId) {
        Store existingStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store with ID: " + storeId + " not found."));
        User currentUser = userService.getCurrentUser();
        User storeOwner = existingStore.getUser();
        // Store owner or Admin can delete
        if (!currentUser.getId().equals(existingStore.getUser().getId()) && !currentUser.getRole().name().equals("ADMIN")) {
            throw new UnauthorizedAccessException("You do not have permission to delete this store.");
        }
        storeRepository.deleteById(storeId);

        // Remove the store from the user
        storeOwner.setStore(null);
        userService.updateUserRole(storeOwner.getId(), Role.USER);
    }

    // Store Owner/Admin: Get store by ID
    @Override
    public StoreResponseDTO getStoreById(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store with ID: " + storeId + " not found."));
        User currentUser = userService.getCurrentUser();
        // Store owner or Admin can view
        if (!currentUser.getId().equals(store.getUser().getId()) && !currentUser.getRole().name().equals("ADMIN")) {
            throw new UnauthorizedAccessException("You do not have permission to view this store.");
        }
        return storeMapper.toResponseDTO(store);
    }

    // Admin: Get all stores
    @Override
    public List<StoreResponseDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return storeMapper.toStoreResponseDTOList(stores);
    }
}
