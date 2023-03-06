package com.jawbr.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jawbr.entity.MagicItem;

import jakarta.transaction.Transactional;

public interface MagicItemRepository extends JpaRepository<MagicItem, Integer> {

	// method to find by indexName, JPA will provide all the code
	public Optional<MagicItem> findByIndexName(String indexName);

	@Modifying
	@Transactional
    @Query("UPDATE MagicItem m SET m.indexName = :#{#magicItem.indexName}, "
    		+ "m.itemName = :#{#magicItem.itemName}, "
    		+ "m.descr = :#{#magicItem.descr}, "
    		+ "m.rarity = :#{#magicItem.rarity}, "
    		+ "m.url = :#{#magicItem.url}, "
    		+ "m.equipmentCategory = :#{#magicItem.equipmentCategory}, "
    		+ "m.sourceBook = :#{#magicItem.sourceBook} "
    		+ "WHERE m.id = :#{#magicItem.id}")
    public void update(@Param("magicItem") MagicItem magicItem);
	
}
