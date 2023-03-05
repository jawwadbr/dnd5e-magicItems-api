package com.jawbr.dto.mapper;

import java.util.function.Function;

import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.entity.EquipmentCategory;

public class EquipmentCategoryDTOMapper implements Function<EquipmentCategory, EquipmentCategoryDTO>{

	@Override
	public EquipmentCategoryDTO apply(EquipmentCategory equipmentCategory) {
		return new EquipmentCategoryDTO(equipmentCategory.getCategoryName());
	}

}
