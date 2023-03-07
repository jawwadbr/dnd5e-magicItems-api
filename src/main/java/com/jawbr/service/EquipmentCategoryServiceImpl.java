package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.EquipmentCategoryRepository;
import com.jawbr.entity.EquipmentCategory;

@Service
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {

	private final EquipmentCategoryRepository equipmentCategoryRepository;
	
	@Autowired
	public EquipmentCategoryServiceImpl(EquipmentCategoryRepository equipmentCategoryRepository) {
		this.equipmentCategoryRepository = equipmentCategoryRepository;
	}

	@Override
	public List<EquipmentCategory> findAll() {
		return equipmentCategoryRepository.findAll();
	}

	@Override
	public EquipmentCategory findById(int id) {
		return equipmentCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Did not find Equipment Category id - " + id)); // Add custom exception later
	}

}
