package com.jawbr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.MagicItemsRepository;
import com.jawbr.entity.MagicItems;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;

@Service
public class MagicItemsServiceImpl implements MagicItemsService {
	
	@Autowired
	private MagicItemsRepository magicItemsRepository;
	
	@Override
	public List<MagicItems> findAll() {
		return magicItemsRepository.findAll();
	}

	@Override
	public MagicItems findByIndexName(String magicItemIndexName) {
		return magicItemsRepository.findByIndexName(magicItemIndexName);
	}

	@Override
	public void save(MagicItems magicItem) {
		magicItemsRepository.save(magicItem);
	}

	@Override
	public MagicItems findById(int magicItemId) {
		
		Optional<MagicItems> result = magicItemsRepository.findById(magicItemId);
		
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new MagicItemNotFoundException("Magic Item with id '" + magicItemId + "' not found");
		}
		
	}

}
