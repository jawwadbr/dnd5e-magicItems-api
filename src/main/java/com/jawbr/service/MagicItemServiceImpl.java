package com.jawbr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jawbr.dao.EquipmentCategoryRepository;
import com.jawbr.dao.MagicItemRepository;
import com.jawbr.dao.SourceBookRepository;
import com.jawbr.dto.MagicItemDTO;
import com.jawbr.dto.mapper.MagicItemDTOMapper;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.entity.MagicItem;
import com.jawbr.entity.SourceBook;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.utils.MergeDescription;

@Service
public class MagicItemServiceImpl implements MagicItemService {
	
	private final MagicItemRepository magicItemsRepository;
	private final EquipmentCategoryRepository equipmentCategoryRepository;
	private final SourceBookRepository sourceBookRepository;
	private final MagicItemDTOMapper magicItemDTOMapper;
	
	@Autowired
	public MagicItemServiceImpl(MagicItemDTOMapper magicItemDTOMapper, 
			MagicItemRepository magicItemsRepository, 
			EquipmentCategoryRepository equipmentCategoryRepository, 
			SourceBookRepository sourceBookRepository) {
		
		this.magicItemDTOMapper = magicItemDTOMapper;
		this.magicItemsRepository = magicItemsRepository;
		this.equipmentCategoryRepository = equipmentCategoryRepository;
		this.sourceBookRepository = sourceBookRepository;
	}
	
	@Override
	public List<MagicItemDTO> findAllMagicItems() {
		return Optional.ofNullable(magicItemsRepository.findAll())
				.map(item -> magicItemDTOMapper.mapToDto(item))
				.filter(list -> !list.isEmpty())
				.orElseThrow(() -> new MagicItemNotFoundException("No Magic Items found inside Database."));
	}

	@Override
	public MagicItemDTO findMagicItemByIndexName(String magicItemIndexName) {
		return magicItemsRepository.findByIndexName(magicItemIndexName)
				.map((MagicItem item) -> magicItemDTOMapper.mapToDto(item))
				.orElseThrow(() -> new MagicItemNotFoundException("Magic Item with Index Name '" + magicItemIndexName + "' not found."));
	}
	
	@Override
	public MagicItemDTO findMagicItemById(int magicItemId) {
		return magicItemsRepository.findById(magicItemId).map(magicItemDTOMapper)
				.orElseThrow(() -> new MagicItemNotFoundException("Magic Item with id '" + magicItemId + "' not found."));
	}

	@Override
	public void save(MagicItemDTO magicItemDto) {
		
		MagicItem newMagicItem = magicItemDTOMapper.toEntity(magicItemDto);
		
		if(newMagicItem.getDescription() != null && !newMagicItem.getDescription().isEmpty()) {
			newMagicItem.setDescr(MergeDescription.mergeDescription(newMagicItem.getDescription()));
		} else {
			newMagicItem.setDescr("");
		}
		
		// Attach entities to Item to persist
		EquipmentCategory equipCat = equipmentCategoryRepository.findByIndexName(magicItemDto.equipmentCategory().indexName())
                .orElseThrow(() -> new RuntimeException("Equipment Category with index name '" + magicItemDto.equipmentCategory().indexName() + "' not found."));
		
		SourceBook srcBook = sourceBookRepository.findBySourceName(magicItemDto.sourceBook().sourceName())
				.orElseThrow(() -> new RuntimeException("Source Book with source name '" + magicItemDto.sourceBook().sourceName() + "' not found."));
				
		newMagicItem.setEquipmentCategory(equipCat);
		newMagicItem.setSourceBook(srcBook);
		
		// just in case the id is passed in JSON
		newMagicItem.setId(0);
		
		magicItemsRepository.save(newMagicItem);
	}

	@Override
	public void update(MagicItem magicItem) {
		
		List<String> descriptions = magicItem.getDescription();
		 
		if(descriptions != null && !descriptions.isEmpty()) { 
			String mergedDescr = MergeDescription.mergeDescription(descriptions);
			magicItem.setDescr(mergedDescr); 
		} 
		else {
			magicItem.setDescr(""); 
		}
		
		magicItemsRepository.update(magicItem);
	}

}
