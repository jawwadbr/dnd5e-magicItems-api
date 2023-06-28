package com.jawbr.controller;

import com.jawbr.dto.MagicItemDTO;
import com.jawbr.dto.request.MagicItemRequest;
import com.jawbr.service.MagicItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/magic-item")
public class MagicItemController {

    public final MagicItemService magicItemService;

    public MagicItemController(MagicItemService magicItemService) {
        this.magicItemService = magicItemService;
    }

    @GetMapping
    public List<MagicItemDTO> findAllMagicItems() {
        return magicItemService.findAllMagicItems();
    }

    // TODO jakarta.persistence.NonUniqueResultException: query did not return a unique result: 2 | FIX
    @GetMapping("/{indexName}")
    public MagicItemDTO findMagicItemByIndexName(@PathVariable String indexName) {
        return magicItemService.findMagicItemByIndexName(indexName);
    }

    // All endpoints below can only be accessed by ADMIN role

    @GetMapping("/id/{id}")
    public MagicItemDTO findMagicItemById(@PathVariable int id) {
        return magicItemService.findMagicItemById(id);
    }

    @PostMapping
    public ResponseEntity<MagicItemDTO> createMagicItem(@Valid @RequestBody MagicItemRequest magicItemRequest) {
        MagicItemDTO response = magicItemService.createMagicItem(magicItemRequest);
        return new ResponseEntity<>(response ,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public MagicItemDTO editMagicItem(@PathVariable int id, @RequestBody MagicItemRequest magicItemRequest) {
        return magicItemService.updateMagicItem(id, magicItemRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMagicItemById(@PathVariable int id) {
        magicItemService.deleteMagicItemById(id);
        return ResponseEntity.noContent().build();
    }

}
