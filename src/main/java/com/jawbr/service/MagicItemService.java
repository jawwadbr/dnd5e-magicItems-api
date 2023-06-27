package com.jawbr.service;

import com.github.slugify.Slugify;
import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.dto.MagicItemDTO;
import com.jawbr.dto.SourceBookDTO;
import com.jawbr.entity.MagicItem;
import com.jawbr.repository.MagicItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagicItemService { // TODO

    private final MagicItemRepository magicItemRepository;
    private final Slugify slugify;

    public MagicItemService(MagicItemRepository magicItemRepository) {
        this.magicItemRepository = magicItemRepository;
        this.slugify = Slugify.builder().build();
    }

    public List<MagicItemDTO> findAllMagicItems() { // TODO try to add pagination. Maybe add filter by name too ?
        List<MagicItem> itemList = Optional.of(magicItemRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new RuntimeException("No magic items found inside Database.")); // TODO add Custom Exception later
        return itemList.stream().map(this::mapToDto).toList();
    }

    // TODO findById/findByIndexName, save, update, delete

    // Utility method
    private MagicItemDTO mapToDto(MagicItem item) {
        EquipmentCategoryDTO equip = EquipmentCategoryDTO.builder()
                .indexName(item.getEquipmentCategory().getIndexName())
                .equipmentName(item.getEquipmentCategory().getEquipmentName())
                .url(item.getEquipmentCategory().getUrl())
                .build();
        SourceBookDTO book = SourceBookDTO.builder()
                .indexName(item.getSourceBook().getIndexName())
                .sourceName(item.getSourceBook().getSourceName())
                .url(item.getSourceBook().getUrl())
                .build();
        return new MagicItemDTO(
                item.getIndexName(),
                item.getItemName(),
                item.getTopDescr(),
                item.getDescr(),
                item.getRarity(),
                equip,
                book,
                item.getUrl());
    }
}
