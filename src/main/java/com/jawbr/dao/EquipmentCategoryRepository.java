package com.jawbr.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jawbr.entity.EquipmentCategory;

public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategory, Integer>{

	public Optional<EquipmentCategory> findByIndexName(String indexName);

}
