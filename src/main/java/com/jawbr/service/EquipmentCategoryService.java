package com.jawbr.service;

import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.exception.EquipmentCategoryNotFoundException;
import com.jawbr.repository.EquipmentCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentCategoryService { // TODO

    private final EquipmentCategoryRepository equipmentCategoryRepository;

    public EquipmentCategoryService(EquipmentCategoryRepository equipmentCategoryRepository) {
        this.equipmentCategoryRepository = equipmentCategoryRepository;
    }

    // TODO findAll, findById, save, update, delete

    public EquipmentCategoryDTO getEquipCategoryByIndexName(String indexName) {
        EquipmentCategory equip = Optional.ofNullable(equipmentCategoryRepository.findByIndexName(indexName))
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        String.format("Equipment Category with index name %s not found", indexName)));
        return mapToDto(equip);
    }

    private EquipmentCategoryDTO mapToDto(EquipmentCategory equipmentCategory) {
        return EquipmentCategoryDTO.builder()
                .indexName(equipmentCategory.getIndexName())
                .equipmentName(equipmentCategory.getEquipmentName())
                .url(equipmentCategory.getUrl()).build();
    }
}
