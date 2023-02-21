package com.jawbr.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.jawbr.jsonViews.NoIdView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment_category")
public class EquipmentCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "indexname")
	private String indexName;
	
	@JsonView(NoIdView.class)
	@Column(name = "name")
	private String categoryName;
	
	public EquipmentCategory() {}

	public EquipmentCategory(int id, String indexName, String categoryName) {
		this.id = id;
		this.indexName = indexName;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}
	
}
