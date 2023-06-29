package com.jawbr.service;

import com.github.slugify.Slugify;
import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.dto.request.EquipmentCategoryRequest;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.exception.EquipmentCategoryNotFoundException;
import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.repository.EquipmentCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class EquipmentCategoryService {

    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final Slugify slugify;

    private static final String EQUIP_URL = "/api/equipment-categories/";

    public EquipmentCategoryService(EquipmentCategoryRepository equipmentCategoryRepository) {
        this.equipmentCategoryRepository = equipmentCategoryRepository;
        this.slugify = Slugify.builder().build();
    }

    public Page<EquipmentCategoryDTO> findAllEquipCategory(Integer page, Integer pageSize, String sortBy) {
        // limits pageSize to 200
        pageSize = Math.min(Optional.ofNullable(pageSize).orElse(15), 200);
        sortBy = Optional.ofNullable(sortBy)
                .filter(s -> !s.isEmpty())
                .orElse("id");
        Page<EquipmentCategory> equipmentCategoryList = Optional.of(
                        equipmentCategoryRepository.findAll(
                                PageRequest.of(
                                        Optional.ofNullable(page).orElse(0),
                                        pageSize,
                                        Sort.Direction.ASC,
                                        sortBy)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        "No Equipment Category found."));
        return equipmentCategoryList.map(this::mapToDto);
    }

    public EquipmentCategoryDTO getEquipCategoryByIndexName(String indexName) {
        EquipmentCategory equip = Optional.ofNullable(equipmentCategoryRepository.findByIndexName(indexName))
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        String.format("Equipment Category with index name %s not found", indexName)));
        return mapToDto(equip);
    }

    public EquipmentCategoryDTO getEquipCategoryById(int id) {
        EquipmentCategory equip = equipmentCategoryRepository.findById(id)
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        String.format("Equipment Category with id '%d' not found", id)));
        return mapToDto(equip);
    }

    public EquipmentCategoryDTO createEquipCategory(EquipmentCategoryRequest equipmentCategoryRequest) {
        isEquipCategoryDuplicate(equipmentCategoryRequest);
        EquipmentCategory newEquip = equipmentCategoryRepository.save(mapToEntity(equipmentCategoryRequest));
        return mapToDto(newEquip);
    }

    public EquipmentCategoryDTO updateEquipCategory(int id, EquipmentCategoryRequest equipmentCategoryRequest) {
        EquipmentCategory equip = equipmentCategoryRepository.findById(id)
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        String.format("Equipment Category with id '%d' not found.", id)
                ));

        isEquipCategoryDuplicate(equipmentCategoryRequest);

        final String equipName = StringUtils.hasText(
                equipmentCategoryRequest.equipmentName()) ? equipmentCategoryRequest.equipmentName() : equip.getEquipmentName();

        EquipmentCategory updatedEquip = EquipmentCategory.builder()
                .id(equip.getId())
                .equipmentName(equipName)
                .indexName(slugify.slugify(equipName))
                .build();
        updatedEquip.setUrl(EQUIP_URL + updatedEquip.getIndexName());

        equipmentCategoryRepository.save(updatedEquip);

        return mapToDto(updatedEquip);
    }

    public void deleteEquipCategoryById(int id) {
        equipmentCategoryRepository.findById(id).ifPresentOrElse(
                equipmentCategoryRepository::delete, () -> {
                    throw new EquipmentCategoryNotFoundException(
                            String.format("Equipment Category with id '%d' not found.", id)
                    );
                }
        );

    }

    // Utility method
    private EquipmentCategoryDTO mapToDto(EquipmentCategory equipmentCategory) {
        return EquipmentCategoryDTO.builder()
                .indexName(equipmentCategory.getIndexName())
                .equipmentName(equipmentCategory.getEquipmentName())
                .url(equipmentCategory.getUrl()).build();
    }

    private EquipmentCategory mapToEntity(EquipmentCategoryRequest equipmentCategoryRequest) {
        EquipmentCategory equip = EquipmentCategory.builder()
                .equipmentName(equipmentCategoryRequest.equipmentName()).build();
        equip.setIndexName(slugify.slugify(equip.getEquipmentName()));
        equip.setUrl(EQUIP_URL + equip.getIndexName());
        return equip;
    }

    private void isEquipCategoryDuplicate(EquipmentCategoryRequest equipmentCategoryRequest) {
        if(Optional.ofNullable(equipmentCategoryRepository.findByIndexName(slugify.slugify(
                equipmentCategoryRequest.equipmentName()))).isPresent()) {
            throw new IntegrityConstraintViolationException(
                    String.format(
                            "Duplicated Equipment Category. The Equipment Category '%s' already exists.",
                            equipmentCategoryRequest.equipmentName()));
        }
    }

}
