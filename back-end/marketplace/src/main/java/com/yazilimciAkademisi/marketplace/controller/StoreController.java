package com.yazilimciAkademisi.marketplace.controller;

import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<StoreResponseDTO>> getAllStores() {
        List<StoreResponseDTO> stores = storeService.getAllStoreDTOs();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> getStoreById(@PathVariable Integer id) {
        StoreResponseDTO storeResponseDTO = storeService.getStoreResponseDTOById(id);
        return ResponseEntity.ok(storeResponseDTO);
    }

    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO createdStore = storeService.saveStore(storeRequestDTO);
        return ResponseEntity.status(201).body(createdStore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> updateStore(@PathVariable Integer id, @RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO updatedStore = storeService.updateStore(id, storeRequestDTO);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

}
