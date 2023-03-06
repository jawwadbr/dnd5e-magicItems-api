package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.SourceBookRepository;
import com.jawbr.entity.SourceBook;

@Service
public class SourceBookServiceImpl implements SourceBookService {
	
	private final SourceBookRepository sourceBookRepository;
	
	@Autowired
	public SourceBookServiceImpl(SourceBookRepository sourceBookRepository) {
		this.sourceBookRepository = sourceBookRepository;
	}

	@Override
	public List<SourceBook> findAll() {
		return sourceBookRepository.findAll();
	}

	@Override
	public SourceBook findById(int id) {
		return sourceBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Did not find Source Book id - " + id)); // Add Custom Exception later
	}

}
