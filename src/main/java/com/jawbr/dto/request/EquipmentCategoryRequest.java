package com.jawbr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EquipmentCategoryRequest(
        @NotBlank(message = "Equipment Category name cannot be empty!") String equipmentName
) {
}
