package com.jawbr.dto;

import java.util.List;

import com.jawbr.entity.EquipmentCategory;
import com.jawbr.entity.SourceBook;

public record MagicItemDTO (
		String indexName,
		String itemName,
		List<String> description,
		String rarity,
		String url,
		EquipmentCategoryDTO equipmentCategory,
		SourceBookDTO sourceBook
		) {

	public MagicItemDTO(String indexName, String itemName, List<String> description, String rarity, String url,
			EquipmentCategory equipCategory, SourceBook sourceBook) {
		this(indexName, itemName, description, rarity, url, new EquipmentCategoryDTO(equipCategory.getCategoryName()), new SourceBookDTO(sourceBook.getSourceName()));
	}

}
