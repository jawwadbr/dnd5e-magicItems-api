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
import com.jawbr.entity.MagicItem;
import com.jawbr.exceptionHandler.MagicItemNotFoundException;
import com.jawbr.service.MagicItemService;

@RestController
@RequestMapping("/api/magic-items")
public class MagicItemController {
	
	private final MagicItemService magicItemsService;
	
	@Autowired
	public MagicItemController(MagicItemService magicItemsService) {
		this.magicItemsService = magicItemsService;
	}

	/**
	 * GET ENDPOINT
	 * 
	 * Returns a list of all magic items
	 * 
	 * @throws MagicItemNotFoundException if the list of items is not found inside the db
	 */
	@GetMapping()
	public List<MagicItemDTO> getAllMagicItems() {
		return magicItemsService.findAllMagicItems();
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
		return magicItemsService.findMagicItemByIndexName(magicItemIndexName);
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
		return magicItemsService.findMagicItemById(magicItemId);
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
	 *	    "equipmentCategory": {
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
	public ResponseEntity<UpdateResponse> saveMagicItem(@RequestBody MagicItemDTO magicItem) {
		magicItemsService.save(magicItem);
		UpdateResponse response = new UpdateResponse(String.valueOf(magicItem.indexName()), magicItem.url());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/*
	 * Endpoint PUT
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Update Magic Item
	 * 
	 * NEED TO UPDATE PUT TO USE DTO
	 */
	@PutMapping()
	public ResponseEntity<UpdateResponse> updateMagicItem(@RequestBody MagicItem magicItem) {
		magicItemsService.update(magicItem);
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
	 *
	 */
	@PutMapping("/{magicItemId}")
	public ResponseEntity<UpdateResponse> updateMagicItem(@RequestBody MagicItem magicItem, @PathVariable int magicItemId) {
		magicItemsService.update(magicItem);
		UpdateResponse response = new UpdateResponse(String.valueOf(magicItem.getId()), magicItem.getUrl());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * Endpoint DELETE "/{magicItemId}"
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Delete Magic Item using PK Id
	 * 
	 */
}
