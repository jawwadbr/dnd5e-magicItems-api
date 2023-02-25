package com.jawbr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.SourceBook;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class SourceBookDAOImpl implements SourceBookDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<SourceBook> getAllSourceBook() {
		
		TypedQuery<SourceBook> query = entityManager.createQuery("SELECT sb FROM SourceBook sb", SourceBook.class);
		
        return query.getResultList();
	}

	@Override
	public SourceBook getSourceBook(int id) {
		
        return entityManager.find(SourceBook.class, id);
	}

}
