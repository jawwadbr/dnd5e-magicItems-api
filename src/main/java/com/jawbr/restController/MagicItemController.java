package com.jawbr.restController;

import java.util.List;

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

import com.jawbr.customResponse.UpdateResponse;
import com.jawbr.dto.MagicItemDTO;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.entity.MagicItem;
import com.jawbr.entity.SourceBook;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.service.EquipmentCategoryService;
import com.jawbr.service.MagicItemService;
import com.jawbr.service.SourceBookService;
import com.jawbr.utils.MergeDescription;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemController {
	
	// REMOVE ALL LOGIC FROM CONTROLLER SHOULD NOT BE HERE WHEN I CAN
	
	@Autowired
	private MagicItemService magicItemsService;
	
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
	public List<MagicItemDTO> getAllMagicItems() {
		return magicItemsService.findAll();
	}
	
	/**
	 * Endpoint GET "/{magicItemIndexName}"
	 * 
	 * Returns the magic item with the specified index name
	 * 
	 * @throws MagicItemNotFoundException if the item is not found
	 */
	@GetMapping("/{magicItemIndexName}")
	public MagicItemDTO getMagicItemByIndexName(@PathVariable String magicItemIndexName) {
		return magicItemsService.findByIndexName(magicItemIndexName);
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
	public MagicItemDTO getMagicItemById(@PathVariable int magicItemId) {
		return magicItemsService.findById(magicItemId);
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
	 * 
	 */
	@PostMapping()
	public ResponseEntity<MagicItem> saveMagicItem(@RequestBody MagicItem magicItem) {
		
		if(magicItem.getDescription() != null && !magicItem.getDescription().isEmpty()) {
		    magicItem.setDescr(MergeDescription.mergeDescription(magicItem.getDescription()));
		} else {
		    magicItem.setDescr("");
		}
		
		// Fetch Equip and SourceBook entity
		EquipmentCategory equipCat = equipmentCategoryService.findById(magicItem.getEquipmentCategory().getId());
		SourceBook srcBook = sourceBookService.findById(magicItem.getSourceBook().getId());
		
		// Attach entities to Item to persist
		magicItem.setEquipmentCategory(equipCat);
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
	public ResponseEntity<UpdateResponse> updateMagicItem(@RequestBody MagicItem magicItem) {
		
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
	 * Need to recreate
	 */

	/*
	 * Endpoint DELETE "{magicItemId}"
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Delete Magic Item using PK Id
	 * 
	 */
}
