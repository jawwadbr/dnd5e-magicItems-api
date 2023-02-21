package com.jawbr.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jawbr.entity.MagicItems;
import com.jawbr.jsonViews.NoIdView;
import com.jawbr.service.MagicItemsService;
import com.jawbr.utils.SplitDescr;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemsController {

	@Autowired
	private MagicItemsService magicItemsService;
	
	// Endpoint GET
	@JsonView(NoIdView.class)
	@GetMapping()
	public List<MagicItems> getAllMagicItems() {
		List<MagicItems> items = magicItemsService.getMagicItems();
		
		SplitDescr.splitDescr(items);
		
		return items;
	}
	
	// Endpoint GET "/{indexName}"
	@JsonView(NoIdView.class)
	@GetMapping("/{indexName}")
	public MagicItems getMagicItem(@PathVariable String indexName) {
		
		MagicItems item = magicItemsService.getMagicItem(indexName);
		
		SplitDescr.splitDescr(item);
		
		return item;
	}
	
}
