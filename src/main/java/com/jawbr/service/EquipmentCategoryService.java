package com.jawbr.service;

import java.util.List;

import com.jawbr.entity.EquipmentCategory;

public interface EquipmentCategoryService {
	
	public List<EquipmentCategory> getAllEquipmentCategory();
	
	public EquipmentCategory getEquipmentCategory(int id);
}