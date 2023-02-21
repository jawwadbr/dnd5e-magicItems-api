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
@Table(name = "source_book")
public class SourceBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@JsonView(NoIdView.class)
	@Column(name = "source_name")
	private String sourceName;
	
	public SourceBook() {}

	public SourceBook(int id, String sourceName) {
		this.id = id;
		this.sourceName = sourceName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Override
	public String toString() {
		return sourceName;
	}
	
	
}
