package com.jawbr.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.jawbr.jsonViews.NoIdView;

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
public class MagicItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@JsonView(NoIdView.class)
	@Column(name = "indexname")
	private String indexName;
	
	@JsonView(NoIdView.class)
	@Column(name = "name")
	private String itemName;
	
	@Column(name = "descr")
	private String descr;
	
	@JsonView(NoIdView.class)
	@Transient
	private List<String> description;
	
	@JsonView(NoIdView.class)
	@Column(name = "rarity")
	private String rarity;
	
	@JsonView(NoIdView.class)
	@Column(name = "url")
	private String url;
	
	@JsonView(NoIdView.class)
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "equipment_category_fk")
	private EquipmentCategory equipCategory;
	
	@JsonView(NoIdView.class)
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "source_name_fk")
	private SourceBook sourceBook;
	
	public MagicItems() {}

	public MagicItems(String indexName, String itemName, String descr, String rarity, String url,EquipmentCategory equipCategory,
			SourceBook sourceBook) {
		this.indexName = indexName;
		this.itemName = itemName;
		this.descr = descr;
		this.rarity = rarity;
		this.url = url;
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

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MagicItems [id=" + id + ", indexName=" + indexName + ", url=" + url + ", itemName=" + itemName
				+ ", descr=" + description + ", rarity=" + rarity + ", equipCategory=" + equipCategory + ", sourceBook="
				+ sourceBook + "]";
	}
	
}
