package com.yazilimciAkademisi.marketplace.service;

import com.yazilimciAkademisi.marketplace.dto.mapper.StoreMapper;
import com.yazilimciAkademisi.marketplace.dto.request.StoreRequestDTO;
import com.yazilimciAkademisi.marketplace.dto.response.StoreResponseDTO;
import com.yazilimciAkademisi.marketplace.entity.Store;
import com.yazilimciAkademisi.marketplace.exception.StoreNotFoundException;
import com.yazilimciAkademisi.marketplace.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<StoreResponseDTO> getAllStoreDTOs() {
        List<Store> stores = storeRepository.findAll();
        return storeMapper.toStoreResponseDTOList(stores);
    }

    public StoreResponseDTO getStoreResponseDTOById(Integer id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store with ID " + id + " does not exist."));
        return storeMapper.toResponseDTO(store);
    }

    public StoreResponseDTO saveStore(StoreRequestDTO storeRequestDTO) {
        Store store = storeMapper.toEntity(storeRequestDTO);
        Store savedStore = storeRepository.save(store);
        return storeMapper.toResponseDTO(savedStore);
    }

    public StoreResponseDTO updateStore(Integer id, StoreRequestDTO storeRequestDTO) {
        Store existingStore = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store with ID " + id + " does not exist."));
        existingStore.setStoreName(storeRequestDTO.getStoreName());
        existingStore.setDescription(storeRequestDTO.getDescription());
        existingStore.setContactInfo(storeRequestDTO.getContactInfo());
        existingStore.setAddress(storeRequestDTO.getAddress());
        Store updatedStore = storeRepository.save(existingStore);
        return storeMapper.toResponseDTO(updatedStore);
    }

    public void deleteStore(Integer id) {
        if (!storeRepository.existsById(id)) {
            throw new StoreNotFoundException("Store with ID " + id + " does not exist.");
        }
        storeRepository.deleteById(id);
    }
}
