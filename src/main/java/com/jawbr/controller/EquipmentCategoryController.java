package com.jawbr.controller;

import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.dto.request.EquipmentCategoryRequest;
import com.jawbr.service.EquipmentCategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipment-categories")
public class EquipmentCategoryController {

    private final EquipmentCategoryService equipmentCategoryService;

    public EquipmentCategoryController(EquipmentCategoryService equipmentCategoryService) {
        this.equipmentCategoryService = equipmentCategoryService;
    }

    @GetMapping
    public Page<EquipmentCategoryDTO> findAllEquipCategory(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy)
    {

        return equipmentCategoryService.findAllEquipCategory(page, pageSize, sortBy);
    }

    @GetMapping("/{indexName}")
    public EquipmentCategoryDTO findEquipCategoryByIndexName(@PathVariable String indexName) {
        return equipmentCategoryService.findEquipCategoryByIndexName(indexName);
    }

    // All endpoints below can only be accessed by ADMIN role

    @GetMapping("/id/{id}")
    public EquipmentCategoryDTO findEquipCategoryById(@PathVariable int id) {
        return equipmentCategoryService.findEquipCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<EquipmentCategoryDTO> createEquipCategory(
            @Valid @RequestBody EquipmentCategoryRequest equipmentCategoryRequest)
    {
        EquipmentCategoryDTO response =
                equipmentCategoryService.createEquipCategory(equipmentCategoryRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public EquipmentCategoryDTO updateEquipCategory(
            @PathVariable int id, @RequestBody EquipmentCategoryRequest equipmentCategoryRequest)
    {
        return equipmentCategoryService.updateEquipCategory(id, equipmentCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipCategoryById(@PathVariable int id) {
        equipmentCategoryService.deleteEquipCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
