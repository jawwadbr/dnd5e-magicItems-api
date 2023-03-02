package com.jawbr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jawbr.entity.MagicItems;

public interface MagicItemsRepository extends JpaRepository<MagicItems, Integer> {

	// method to find by indexName, JPA will provide all the code
	public MagicItems findByIndexName(String indexName);
	
}
