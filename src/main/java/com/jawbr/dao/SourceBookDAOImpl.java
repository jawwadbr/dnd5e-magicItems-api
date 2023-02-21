package com.jawbr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jawbr.entity.SourceBook;

import jakarta.persistence.EntityManager;

@Repository
public class SourceBookDAOImpl implements SourceBookDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<SourceBook> getAllSourceBook() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<SourceBook> query = session.createQuery("from SourceBook", SourceBook.class);
		
		List<SourceBook> books = query.getResultList();
		
		return books;
	}

	@Override
	public SourceBook getSourceBook(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(SourceBook.class, id);
	}

}
