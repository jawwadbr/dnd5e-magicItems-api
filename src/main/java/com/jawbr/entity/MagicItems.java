package com.jawbr.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "magic_items")
public class MagicItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "indexname")
	private String indexName;
	
	@Column(name = "name")
	private String itemName;
	
	@Column(name = "descr")
	private String descr;
	
	@Column(name = "rarity")
	private String rarity;
	
	@Column(name = "url")
	private String url;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "equipment_category_fk")
	private EquipmentCategory equipCategory;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "source_name_fk")
	private SourceBook sourceBook;
	
	public MagicItems() {}

	public MagicItems(String itemName, String descr, String rarity, EquipmentCategory equipCategory,
			SourceBook sourceBook) {
		this.itemName = itemName;
		this.descr = descr;
		this.rarity = rarity;
		this.equipCategory = equipCategory;
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

	public EquipmentCategory getEquipCategory() {
		return equipCategory;
	}

	public void setEquipCategory(EquipmentCategory equipCategory) {
		this.equipCategory = equipCategory;
	}

	public SourceBook getSourceBook() {
		return sourceBook;
	}

	public void setSourceBook(SourceBook sourceBook) {
		this.sourceBook = sourceBook;
	}

	@Override
	public String toString() {
		return "MagicItems [id=" + id + ", indexName=" + indexName + ", url=" + url + ", itemName=" + itemName
				+ ", descr=" + descr + ", rarity=" + rarity + ", equipCategory=" + equipCategory + ", sourceBook="
				+ sourceBook + "]";
	}
	
	
}
