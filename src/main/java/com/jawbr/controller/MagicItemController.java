package com.jawbr.controller;

import com.jawbr.dto.MagicItemDTO;
import com.jawbr.service.MagicItemService;
import org.springframework.web.bind.annotation.GetMapping;
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

}
