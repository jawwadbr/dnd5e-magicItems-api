package com.jawbr.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "magic_items")
public class MagicItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "indexname")
	private String indexName;
	
	@Column(name = "name")
	private String itemName;
	
	@JsonIgnore
	@Column(name = "descr")
	private String descr;
	
	@Transient
	private List<String> description;
	
	@Column(name = "rarity")
	private String rarity;
	
	@Column(name = "url")
	private String url;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "equipment_category_fk")
	private EquipmentCategory equipmentCategory;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "source_name_fk")
	private SourceBook sourceBook;
	
	public MagicItem() {}

	public MagicItem(String indexName, String itemName, String descr, String rarity, String url,EquipmentCategory equipmentCategory,
			SourceBook sourceBook) {
		this.indexName = indexName;
		this.itemName = itemName;
		this.descr = descr;
		this.rarity = rarity;
		this.url = url;
		this.equipmentCategory = equipmentCategory;
		this.sourceBook = sourceBook;
	}

	public MagicItem(int id, String indexName, String itemName, String descr, List<String> description, String rarity,
			String url, EquipmentCategory equipmentCategory, SourceBook sourceBook) {
		this.id = id;
		this.indexName = indexName;
		this.itemName = itemName;
		this.descr = descr;
		this.description = description;
		this.rarity = rarity;
		this.url = url;
		this.equipmentCategory = equipmentCategory;
		this.sourceBook = sourceBook;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EquipmentCategory getEquipmentCategory() {
		return equipmentCategory;
	}

	public void setEquipmentCategory(EquipmentCategory equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}

	public SourceBook getSourceBook() {
		return sourceBook;
	}

	public void setSourceBook(SourceBook sourceBook) {
		this.sourceBook = sourceBook;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MagicItems [id=" + id + ", indexName=" + indexName + ", itemName=" + itemName + ", description="
				+ description + ", rarity=" + rarity + ", url=" + url + ", equipmentCategory=" + equipmentCategory
				+ ", sourceBook=" + sourceBook + "]";
	}
	
}
