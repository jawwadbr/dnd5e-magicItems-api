package com.jawbr.repository;

import com.jawbr.entity.MagicItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagicItemRepository extends JpaRepository<MagicItem, Integer> {

    MagicItem findByIndexName(String indexName);
}
