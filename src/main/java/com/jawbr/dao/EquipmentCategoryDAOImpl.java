package com.jawbr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.EquipmentCategory;

import jakarta.persistence.EntityManager;

@Repository
public class EquipmentCategoryDAOImpl implements EquipmentCategoryDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<EquipmentCategory> getAllEquipmentCategory() {

		Session session = entityManager.unwrap(Session.class);
		
		Query<EquipmentCategory> query = session.createQuery("from EquipmentCategory", EquipmentCategory.class);
		
		List<EquipmentCategory> equips = query.getResultList();
		
		return equips;
	}

	@Override
	public EquipmentCategory getEquipmentCategory(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(EquipmentCategory.class, id);
	}

}
