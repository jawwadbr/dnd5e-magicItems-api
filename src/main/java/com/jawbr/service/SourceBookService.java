package com.jawbr.service;

import java.util.List;

import com.jawbr.entity.SourceBook;

public interface SourceBookService {
	
	public List<SourceBook> getAllSourceBook();
	
	public SourceBook getSourceBook(int id);
}
