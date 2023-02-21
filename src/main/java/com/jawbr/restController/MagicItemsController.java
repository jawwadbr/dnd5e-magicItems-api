package com.jawbr.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jawbr.entity.MagicItems;
import com.jawbr.service.MagicItemsService;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemsController {

	@Autowired
	private MagicItemsService magicItemsService;
	
	// Endpoint GET
	@GetMapping()
	public List<MagicItems> getAllMagicItems() {
		return magicItemsService.getMagicItems();
	}
	
	// Endpoint GET "/{indexName}"
	@GetMapping("/{indexName}")
	public MagicItems getMagicItem(@PathVariable String indexName) {
		return magicItemsService.getMagicItem(indexName);
	}
	
}
