package com.jawbr.service;

import java.util.List;

import com.jawbr.dto.MagicItemDTO;
import com.jawbr.entity.MagicItem;

public interface MagicItemService {

	public List<MagicItemDTO> findAll();

	public MagicItemDTO findByIndexName(String indexName);
	
	public void save(MagicItem magicItem);

	public MagicItemDTO findById(int magicItemId);
}
