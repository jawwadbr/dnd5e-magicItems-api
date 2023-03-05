package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.MagicItemRepository;
import com.jawbr.dto.MagicItemDTO;
import com.jawbr.dto.mapper.MagicItemDTOMapper;
import com.jawbr.entity.MagicItem;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.utils.SplitDescr;

@Service
public class MagicItemServiceImpl implements MagicItemService {
	
	@Autowired
	private MagicItemRepository magicItemsRepository;
	
	private final MagicItemDTOMapper magicItemDTOMapper;
	
	public MagicItemServiceImpl(MagicItemDTOMapper magicItemDTOMapper) {
		this.magicItemDTOMapper = magicItemDTOMapper;
	}
	
	@Override
	public List<MagicItemDTO> findAll() {
		
		List<MagicItem> items = magicItemsRepository.findAll();
		
		if(items == null || items.isEmpty()) {
			throw new MagicItemNotFoundException("No Magic Items found inside Database.");
		}
		
		return magicItemDTOMapper.mapToDto(items);
	}

	@Override
	public MagicItemDTO findByIndexName(String magicItemIndexName) {
		
		MagicItem item = magicItemsRepository.findByIndexName(magicItemIndexName);
		
		if(item == null){
			throw new MagicItemNotFoundException("Magic Item with Index Name '" + magicItemIndexName + "' not found.");
		}
		
		return magicItemDTOMapper.mapToDto(item);
	}
	
	@Override
	public MagicItemDTO findById(int magicItemId) {
		
		MagicItemDTO item = magicItemsRepository.findById(magicItemId).map(magicItemDTOMapper)
				.orElseThrow(() -> new MagicItemNotFoundException("Magic Item with id '" + magicItemId + "' not found."));
		
		return item;
	}

	@Override
	public void save(MagicItem magicItem) {
		SplitDescr.splitDescr(magicItem);
		magicItemsRepository.save(magicItem);
	}

}
