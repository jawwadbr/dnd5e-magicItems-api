package com.jawbr.service;

import java.util.List;
import java.util.Optional;

import com.jawbr.dto.MagicItemDTO;
import com.jawbr.entity.MagicItem;

public interface MagicItemService {

	public List<MagicItemDTO> findAllMagicItems();

	public MagicItemDTO findMagicItemByIndexName(String indexName);
	
	public MagicItemDTO findMagicItemById(int magicItemId);
	
	void save(MagicItemDTO magicItem);
	
	public void update(Optional<MagicItem> magicItem);

	public void deleteMagicItem(int magicItemId);

}
