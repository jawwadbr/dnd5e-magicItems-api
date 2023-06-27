package com.jawbr.dto;

import lombok.Builder;

@Builder
public record EquipmentCategoryDTO(
        String indexName,
        String equipmentName,
        String url
) {
}
