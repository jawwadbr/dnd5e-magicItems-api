package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jawbr.dao.SourceBookDAO;
import com.jawbr.entity.SourceBook;

@Service
public class SourceBookServiceImpl implements SourceBookService {
	
	@Autowired
	private SourceBookDAO sourceBookDAO;

	@Override
	@Transactional
	public List<SourceBook> getAllSourceBook() {
		return sourceBookDAO.getAllSourceBook();
	}

	@Override
	@Transactional(readOnly = true)
	public SourceBook getSourceBook(int id) {
		return sourceBookDAO.getSourceBook(id);
	}

}
