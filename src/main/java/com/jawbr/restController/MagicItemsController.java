package com.jawbr.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jawbr.entity.MagicItems;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.jsonViews.NoIdView;
import com.jawbr.service.MagicItemsService;
import com.jawbr.utils.SplitDescr;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemsController {

	@Autowired
	private MagicItemsService magicItemsService;
	
	/**
	 * GET ENDPOINT
	 * 
	 * Returns a list of all magic items
	 * 
	 * @throws MagicItemNotFoundException if the list of items is not found inside the db
	 */
	@JsonView(NoIdView.class)
	@GetMapping()
	public List<MagicItems> getAllMagicItems() {
		
		List<MagicItems> items = magicItemsService.getMagicItems();
		
		// Throw exception if no items found
        if (items == null || items.isEmpty()) {
            throw new MagicItemNotFoundException("No Magic Items found inside Database!");
        }
		
		SplitDescr.splitDescr(items);
		
		return items;
	}
	
	/**
	 * Endpoint GET "/{magicItemIndexName}"
	 * 
	 * Returns the magic item with the specified index name
	 * 
	 * @throws MagicItemNotFoundException if the item is not found
	 */
	@JsonView(NoIdView.class)
	@GetMapping("/{magicItemIndexName}")
	public MagicItems getMagicItem(@PathVariable String magicItemIndexName) {
		
		MagicItems item = magicItemsService.getMagicItem(magicItemIndexName);
		
		// Throw exception if no item found
        if (item == null) {
            throw new MagicItemNotFoundException("Magic Item with Index Name '" + magicItemIndexName + "' not found");
        }
		
		SplitDescr.splitDescr(item);
		
		return item;
	}
	
}
