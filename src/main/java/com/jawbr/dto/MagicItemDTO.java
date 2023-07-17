package com.jawbr.dto;

import lombok.Builder;

@Builder
public record MagicItemDTO(
        String indexName,
        String itemName,
        String topDescr,
        String descr,
        String rarity,
        EquipmentCategoryDTO equipmentType,
        SourceBookDTO sourceBook,
        String url) {
}
