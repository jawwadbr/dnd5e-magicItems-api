package com.jawbr.service;

import java.util.List;

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
		
		List<MagicItems> items = magicItemsRepository.findAll();
		
		if(items == null || items.isEmpty()) {
			throw new MagicItemNotFoundException("No Magic Items found inside Database.");
		}
		
		return items;
	}

	@Override
	public MagicItems findByIndexName(String magicItemIndexName) {
		
		MagicItems item = magicItemsRepository.findByIndexName(magicItemIndexName);
		
		if(item == null){
			throw new MagicItemNotFoundException("Magic Item with Index Name '" + magicItemIndexName + "' not found.");
		}
		
		return item;
	}

	@Override
	public void save(MagicItems magicItem) {
		magicItemsRepository.save(magicItem);
	}

	@Override
	public MagicItems findById(int magicItemId) {
		return magicItemsRepository.findById(magicItemId).orElseThrow(() -> new MagicItemNotFoundException("Magic Item with id '" + magicItemId + "' not found."));
	}

}
