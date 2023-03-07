package com.jawbr.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jawbr.customResponse.CustomResponse;
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
	 * 
	 * @RequestParam(required = false) Integer page
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
	 *	        "indexName": "armor",
	 *	        "categoryName": "Armor"
	 *	    },
	 *	    "sourceBook": {
	 *	        "sourceName": "Dungeon Master’s Guide"
	 *	    }
	 *	}
	 * 
	 * 
	 */
	@PostMapping()
	public ResponseEntity<CustomResponse> saveMagicItem(@RequestBody MagicItemDTO magicItem) {
		magicItemsService.save(magicItem);
		CustomResponse response = new CustomResponse(HttpStatus.CREATED.value(),
				magicItem.url(), 
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/*
	 * Endpoint PUT
	 * 
	 * Access restricted to ADMIN only.
	 * 
	 * Update Magic Item
	 * 
	 * JSON exemple
	 * 
	 *	{
	 *		"id" : 1,
	 *	    "indexName": "test",
	 *	    "itemName": "test",
	 *	    "description": [
     *  		"test",
     *  		"newlinedescr424242424"
     *		],
	 *	    "rarity": "Varies",
	 *	    "url": "/api/magic-items/test",
	 *	    "equipmentCategory": {
	 *	        "indexName": "armor",
	 *	        "categoryName": "Armor"
	 *	    },
	 *	    "sourceBook": {
	 *	        "sourceName": "Dungeon Master’s Guide"
	 *	    }
	 *	}
	 * 
	 * I will no longer use DTO in PUT. I might change that later, but since only admins have access to this, I might let it be like this. I need to think about it more carefully later
	 */
	@PutMapping()
	public ResponseEntity<CustomResponse> updateMagicItem(@RequestBody Optional<MagicItem> magicItem) {
		magicItemsService.update(magicItem);
		CustomResponse response = new CustomResponse(HttpStatus.OK.value(), 
				magicItem.get().getUrl(), 
				System.currentTimeMillis());
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
	public ResponseEntity<CustomResponse> updateMagicItem(@RequestBody Optional<MagicItem> magicItem, @PathVariable int magicItemId) {
		magicItemsService.update(magicItem);
		CustomResponse response = new CustomResponse(HttpStatus.OK.value(), 
				magicItem.get().getUrl(), 
				System.currentTimeMillis());
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
	@DeleteMapping("/{magicItemId}")
	public ResponseEntity<Void> deleteMagicItem(@PathVariable int magicItemId) {
		magicItemsService.deleteMagicItem(magicItemId);
		return ResponseEntity.noContent().build();
	}
}
