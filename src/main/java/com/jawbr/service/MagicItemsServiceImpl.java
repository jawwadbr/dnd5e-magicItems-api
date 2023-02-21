package com.jawbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jawbr.dao.MagicItemsDAO;
import com.jawbr.entity.MagicItems;

@Service
public class MagicItemsServiceImpl implements MagicItemsService {

	@Autowired
	private MagicItemsDAO magicItemsDAO;
	
	@Override
	@Transactional
	public List<MagicItems> getMagicItems() {
		return magicItemsDAO.getMagicItems();
	}

	@Override
	public MagicItems getMagicItem(String indexName) {
		return magicItemsDAO.getMagicItem(indexName);
	}

}
