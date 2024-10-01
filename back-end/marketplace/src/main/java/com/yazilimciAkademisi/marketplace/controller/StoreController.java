package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // Store Owner: Create a new store (each user can only have one store)
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StoreRequestDTO requestDTO) {
        StoreResponseDTO createdStore = storeService.createStore(requestDTO);
        return ResponseEntity.status(201).body(createdStore);
    }

    // Store Owner: Update their own store
    @PreAuthorize("hasAuthority('STORE_OWNER')")
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> updateStore(
            @PathVariable Integer storeId,
            @RequestBody StoreRequestDTO requestDTO) {
        StoreResponseDTO updatedStore = storeService.updateStore(storeId, requestDTO);
        return ResponseEntity.ok(updatedStore);
    }

    // Store Owner/Admin: Delete a store
    @PreAuthorize("hasAuthority('STORE_OWNER') or hasAuthority('ADMIN')")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

    // Store Owner/Admin: Get a store by its ID
    @PreAuthorize("hasAuthority('STORE_OWNER') or hasAuthority('ADMIN')")
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDTO> getStoreById(@PathVariable Integer storeId) {
        StoreResponseDTO storeResponse = storeService.getStoreById(storeId);
        return ResponseEntity.ok(storeResponse);
    }

    // Admin: Get all stores
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StoreResponseDTO>> getAllStores() {
        List<StoreResponseDTO> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }
}
