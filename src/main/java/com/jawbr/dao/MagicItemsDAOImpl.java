package com.jawbr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.MagicItems;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MagicItemsDAOImpl implements MagicItemsDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<MagicItems> getMagicItems() {
		
		TypedQuery<MagicItems> query = entityManager.createQuery("SELECT mi from MagicItems mi", MagicItems.class);
		
		return query.getResultList();
	}

	@Override
	public MagicItems getMagicItem(String indexName) {
		
		TypedQuery<MagicItems> query = entityManager.createQuery("SELECT mi FROM MagicItems mi WHERE mi.indexName = :indexName", 
				MagicItems.class).setParameter("indexName", indexName);
		
		return query.getResultStream().findFirst().orElse(null);
	}

}
