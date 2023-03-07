package com.jawbr.service;

import java.util.List;

import com.jawbr.entity.EquipmentCategory;

public interface EquipmentCategoryService {
	
	public List<EquipmentCategory> findAll();
	
	public EquipmentCategory findById(int id);
}
