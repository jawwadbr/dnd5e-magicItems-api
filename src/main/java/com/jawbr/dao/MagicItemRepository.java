package com.jawbr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jawbr.entity.MagicItem;

public interface MagicItemRepository extends JpaRepository<MagicItem, Integer> {

	// method to find by indexName, JPA will provide all the code
	public MagicItem findByIndexName(String indexName);
	
}
