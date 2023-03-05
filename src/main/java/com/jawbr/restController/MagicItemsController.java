package com.jawbr.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jawbr.customResponse.UpdateResponse;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.entity.MagicItems;
import com.jawbr.entity.SourceBook;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.jsonViews.NoIdView;
import com.jawbr.service.EquipmentCategoryService;
import com.jawbr.service.MagicItemsService;
import com.jawbr.service.SourceBookService;
import com.jawbr.utils.MergeDescription;
import com.jawbr.utils.SplitDescr;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemsController {
	
	@Autowired
	private MagicItemsService magicItemsService;
	
	@Autowired
	private EquipmentCategoryService equipmentCategoryService;
	
	@Autowired
	private SourceBookService sourceBookService;
	
	/**
	 * GET ENDPOINT
	 * 
	 * Returns a list of all magic items
	 * 
	 * @throws MagicItemNotFoundException if the list of items is not found inside the db
	 */
	@GetMapping()
	@JsonView(NoIdView.class)
	public ResponseEntity<List<MagicItems>> getAllMagicItems() {
		
		List<MagicItems> items = magicItemsService.findAll();
		SplitDescr.splitDescr(items);
		
		return ResponseEntity.ok(items);
	}
	
	/**
	 * Endpoint GET "/{magicItemIndexName}"
	 * 
	 * Returns the magic item with the specified index name
	 * 
	 * @throws MagicItemNotFoundException if the item is not found
	 */
	@GetMapping("/{magicItemIndexName}")
	@JsonView(NoIdView.class)
	public ResponseEntity<MagicItems> getMagicItemByIndexName(@PathVariable String magicItemIndexName) {
		
		MagicItems item = magicItemsService.findByIndexName(magicItemIndexName);
		SplitDescr.splitDescr(item);
		
		return ResponseEntity.ok(item);
	}
	
	/**
	 * Endpoint GET "/id/{magicItemId}"
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Returns the magic item with the specified id
	 * 
	 * @throws MagicItemNotFoundException if the item is not found
	 */
	@GetMapping("/id/{magicItemId}")
	public ResponseEntity<MagicItems> getMagicItemById(@PathVariable int magicItemId) {
		
		MagicItems item = magicItemsService.findById(magicItemId);
		SplitDescr.splitDescr(item);
		
		return ResponseEntity.ok(item);
	}
	
	/*
	 * Endpoint POST
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Create the magic item and save in the DB
	 * 
	 * JSON exemple
	 * 
	 *	{
	 *	    "indexName": "test",
	 *	    "itemName": "test",
	 *	    "description": [
     *  		"test",
     *  		"newlinedescr424242424"
     *		],
	 *	    "rarity": "Varies",
	 *	    "url": "/api/magic-items/test",
	 *	    "equipCategory": {
	 *	        "id" : 1,
	 *	        "categoryName": "Armor"
	 *	    },
	 *	    "sourceBook": {
	 *	        "id" : 2,
	 *	        "sourceName": "Dungeon Master’s Guide"
	 *	    }
	 *	}
	 * 
	 */
	@PostMapping()
	public ResponseEntity<MagicItems> saveMagicItem(@RequestBody MagicItems magicItem) {
		
		if(magicItem.getDescription() != null && !magicItem.getDescription().isEmpty()) {
		    magicItem.setDescr(MergeDescription.mergeDescription(magicItem.getDescription()));
		} else {
		    magicItem.setDescr("");
		}
		
		// Fetch Equip and SourceBook entity
		EquipmentCategory equipCat = equipmentCategoryService.findById(magicItem.getEquipCategory().getId());
		SourceBook srcBook = sourceBookService.findById(magicItem.getSourceBook().getId());
		
		// Attach entities to Item to persist
		magicItem.setEquipCategory(equipCat);
		magicItem.setSourceBook(srcBook);
		
		// just in case the id is passed in JSON
		magicItem.setId(0);
		
		magicItemsService.save(magicItem);
		
		return new ResponseEntity<>(magicItem, HttpStatus.CREATED);
	}
	
	/*
	 * Endpoint PUT
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Update Magic Item
	 * 
	 */
	@PutMapping()
	public ResponseEntity<UpdateResponse> updateMagicItem(@RequestBody MagicItems magicItem) {
		
		List<String> descriptions = magicItem.getDescription();
		
		if(descriptions != null && !descriptions.isEmpty()) {
			String mergedDescr = MergeDescription.mergeDescription(descriptions);
		    magicItem.setDescr(mergedDescr);
		} else {
		    magicItem.setDescr("");
		}
		
		magicItemsService.save(magicItem);
		
		UpdateResponse response = new UpdateResponse(String.valueOf(magicItem.getId()), magicItem.getUrl());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * Endpoint PUT "/{magicItemId}"
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Update Magic Item using PK Id
	 * 
	 */
	@PutMapping("/{magicItemId}")
	public ResponseEntity<UpdateResponse> updateMagicItem(@RequestBody MagicItems magicItem, @PathVariable int magicItemId) {
		
		MagicItems updatedItem = magicItemsService.findById(magicItemId);
		
		// Need to create a DTO for more clean code
		updatedItem.setItemName(magicItem.getItemName());
		updatedItem.setIndexName(magicItem.getIndexName());
		updatedItem.setRarity(magicItem.getRarity());
		updatedItem.setUrl(magicItem.getUrl());
		updatedItem.setEquipCategory(magicItem.getEquipCategory());
		updatedItem.setSourceBook(magicItem.getSourceBook());
		
		// Merge the descriptions of the new and existing magic items
		String mergedDescr2 = MergeDescription.mergeDescription(magicItem.getDescription());
		updatedItem.setDescr(Optional.ofNullable(mergedDescr2).orElse(""));
		
		// Save the updated Item
		magicItemsService.save(updatedItem);
		
		UpdateResponse response = new UpdateResponse(String.valueOf(updatedItem.getId()), updatedItem.getUrl());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * Endpoint DELETE "{magicItemId}"
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Delete Magic Item using PK Id
	 * 
	 */
}
