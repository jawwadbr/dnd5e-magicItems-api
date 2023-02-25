package com.jawbr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.EquipmentCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EquipmentCategoryDAOImpl implements EquipmentCategoryDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<EquipmentCategory> getAllEquipmentCategory() {
		
		TypedQuery<EquipmentCategory> query = entityManager.createQuery("SELECT ec FROM EquipmentCategory ec", EquipmentCategory.class);
		
		return query.getResultList();
	}

	@Override
	public EquipmentCategory getEquipmentCategory(int id) {
		
		return entityManager.find(EquipmentCategory.class, id);
	}

}
