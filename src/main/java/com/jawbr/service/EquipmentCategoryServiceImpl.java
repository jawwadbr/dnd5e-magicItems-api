package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jawbr.dao.EquipmentCategoryDAO;
import com.jawbr.entity.EquipmentCategory;

@Service
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {

	@Autowired
	private EquipmentCategoryDAO equipCategoryDAO;
	
	@Override
	@Transactional
	public List<EquipmentCategory> getAllEquipmentCategory() {
		return equipCategoryDAO.getAllEquipmentCategory();
	}

	@Override
	@Transactional(readOnly = true)
	public EquipmentCategory getEquipmentCategory(int id) {
		return equipCategoryDAO.getEquipmentCategory(id);
	}

}
