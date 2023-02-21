package com.jawbr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.MagicItems;

import jakarta.persistence.EntityManager;

@Repository
public class MagicItemsDAOImpl implements MagicItemsDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<MagicItems> getMagicItems() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<MagicItems> query = session.createQuery("from MagicItems", MagicItems.class);
		
		List<MagicItems> magicItems = query.getResultList();
		
		return magicItems;
	}

	@Override
	public MagicItems getMagicItem(String indexName) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<MagicItems> query = session.createQuery("from MagicItems where indexName=:indexName", MagicItems.class);
		query.setParameter("indexName", indexName);
		
		MagicItems item = query.getSingleResultOrNull();
		
		return item;
	}

}
