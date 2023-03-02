package com.jawbr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.SourceBookRepository;
import com.jawbr.entity.SourceBook;

@Service
public class SourceBookServiceImpl implements SourceBookService {
	
	@Autowired
	private SourceBookRepository sourceBookRepository;

	@Override
	public List<SourceBook> findAll() {
		return sourceBookRepository.findAll();
	}

	@Override
	public SourceBook findById(int id) {

		Optional<SourceBook> result = sourceBookRepository.findById(id);
		
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new RuntimeException("Did not find Source Book id - " + id); // Add custom exception later
		}
		
	}

}
