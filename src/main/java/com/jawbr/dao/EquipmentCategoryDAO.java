package com.jawbr.dao;

import java.util.List;

import com.jawbr.entity.EquipmentCategory;

public interface EquipmentCategoryDAO {

	public List<EquipmentCategory> getAllEquipmentCategory();
	
	public EquipmentCategory getEquipmentCategory(int id);
}
