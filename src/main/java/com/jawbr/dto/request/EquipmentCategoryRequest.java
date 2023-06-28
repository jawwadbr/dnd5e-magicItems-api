package com.jawbr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EquipmentCategoryRequest(
        @NotBlank(message = "Equipment category name cannot be empty!") String equipmentName
) {
}
