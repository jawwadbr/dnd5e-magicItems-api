package com.jawbr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.EquipmentCategoryRepository;
import com.jawbr.entity.EquipmentCategory;

@Service
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {

	@Autowired
	private EquipmentCategoryRepository equipmentCategoryRepository;
	
	@Override
	public List<EquipmentCategory> findAll() {
		return equipmentCategoryRepository.findAll();
	}

	@Override
	public EquipmentCategory findById(int id) {
		
		Optional<EquipmentCategory> result = equipmentCategoryRepository.findById(id);
		
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Did not find Equipment Category id - " + id); // Add custom exception later
		}
		
	}

}
