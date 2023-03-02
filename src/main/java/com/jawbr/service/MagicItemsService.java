package com.jawbr.service;

import java.util.List;

import com.jawbr.entity.MagicItems;

public interface MagicItemsService {

	public List<MagicItems> findAll();

	public MagicItems findByIndexName(String indexName);
	
	public void save(MagicItems magicItem);
}
