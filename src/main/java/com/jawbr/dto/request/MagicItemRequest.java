package com.jawbr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MagicItemRequest(
        @NotBlank(message = "Item name cannot be empty!") String itemName,
        @NotBlank(message = "Top Description cannot be empty!") String topDescr,
        @NotBlank(message = "Description cannot be empty!") String descr,
        @NotBlank(message = "Rarity of the item cannot be empty") String rarity,
        @NotBlank(message = "Equipment Category name cannot be empty!") String equipmentName,
        @NotBlank(message = "The source book cannot be empty") String sourceName
) {
}
