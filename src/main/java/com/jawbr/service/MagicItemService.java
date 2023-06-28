package com.jawbr.service;

import com.github.slugify.Slugify;
import com.jawbr.dto.EquipmentCategoryDTO;
import com.jawbr.dto.MagicItemDTO;
import com.jawbr.dto.SourceBookDTO;
import com.jawbr.dto.request.MagicItemRequest;
import com.jawbr.entity.EquipmentCategory;
import com.jawbr.entity.MagicItem;
import com.jawbr.entity.SourceBook;
import com.jawbr.exception.EquipmentCategoryNotFoundException;
import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.exception.MagicItemNotFoundException;
import com.jawbr.exception.RarityEnumInvalidException;
import com.jawbr.exception.SourceBookExceptionNotFound;
import com.jawbr.repository.EquipmentCategoryRepository;
import com.jawbr.repository.MagicItemRepository;
import com.jawbr.repository.SourceBookRepository;
import com.jawbr.utils.Rarity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MagicItemService {

    private final MagicItemRepository magicItemRepository;
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final SourceBookRepository sourceBookRepository;
    private final Slugify slugify;

    private static final String MAGIC_URL = "/api/magic-items/";

    public MagicItemService(MagicItemRepository magicItemRepository,
                            EquipmentCategoryRepository equipmentCategoryRepository,
                            SourceBookRepository sourceBookRepository)
    {
        this.magicItemRepository = magicItemRepository;
        this.equipmentCategoryRepository = equipmentCategoryRepository;
        this.sourceBookRepository = sourceBookRepository;
        this.slugify = Slugify.builder().build();
    }

    public List<MagicItemDTO> findAllMagicItems() { // TODO try to add pagination. Maybe add filter by name too ?
        List<MagicItem> itemList = Optional.of(magicItemRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new MagicItemNotFoundException("No magic items found inside Database."));
        return itemList.stream().map(this::mapToDto).toList();
    }

    public MagicItemDTO findMagicItemByIndexName(String indexName) {
        MagicItem item = Optional.ofNullable(
                        magicItemRepository.findByIndexName(indexName))
                .orElseThrow(() -> new MagicItemNotFoundException(String.format("Magic item with index name '%s' not found.", indexName)));
        return mapToDto(item);
    }

    public MagicItemDTO findMagicItemById(int id) {
        MagicItem item = magicItemRepository.findById(id)
                .orElseThrow(() ->
                        new MagicItemNotFoundException(
                                String.format("Magic item with index name '%d' not found.", id)));
        return mapToDto(item);
    }

    public MagicItemDTO createMagicItem(MagicItemRequest magicItemRequest) {
        isMagicItemDuplicated(magicItemRequest);
        isMagicItemHaveValidRarity(magicItemRequest.rarity());

        MagicItem item = mapToEntity(magicItemRequest);
        item.setEquipmentCategory(
                getEquipCategoryFromRequest(
                        magicItemRequest.equipmentName(),
                        magicItemRequest.itemName()));
        item.setSourceBook(
                getSourceBookFromRequest(
                        magicItemRequest.sourceName(),
                        magicItemRequest.itemName()));

        MagicItem newMagicItem = magicItemRepository.save(item);
        return mapToDto(newMagicItem);
    }

    public MagicItemDTO updateMagicItem(int id, MagicItemRequest magicItemRequest) {
        MagicItem item = magicItemRepository.findById(id).orElseThrow(
                () -> new MagicItemNotFoundException(
                        String.format("Magic item with id '%d' not found.", id)
                ));

        isMagicItemDuplicated(magicItemRequest);

        final String itemName = StringUtils.hasText(magicItemRequest.itemName()) ? magicItemRequest.itemName() : item.getItemName();
        final String topDescr = StringUtils.hasText(magicItemRequest.topDescr()) ? magicItemRequest.topDescr() : item.getTopDescr();
        final String descr = StringUtils.hasText(magicItemRequest.descr()) ? magicItemRequest.descr() : item.getDescr();
        final String rarity = StringUtils.hasText(magicItemRequest.rarity()) ? magicItemRequest.rarity() : item.getRarity();

        final String equipName = StringUtils
                .hasText(magicItemRequest.equipmentName()) ?
                magicItemRequest.equipmentName() : item.getEquipmentCategory().getEquipmentName();

        final String bookName = StringUtils.hasText(
                magicItemRequest.sourceName()) ?
                magicItemRequest.sourceName() : item.getSourceBook().getSourceName();

        isMagicItemHaveValidRarity(rarity);

        EquipmentCategory equip = getEquipCategoryFromRequest(equipName, itemName);
        SourceBook book = getSourceBookFromRequest(bookName, itemName);

        MagicItem updatedMagicItem = MagicItem.builder()
                .id(item.getId())
                .itemName(itemName)
                .topDescr(topDescr)
                .descr(descr)
                .rarity(rarity)
                .equipmentCategory(equip)
                .sourceBook(book)
                .build();
        updatedMagicItem.setIndexName(slugify.slugify(updatedMagicItem.getItemName()));
        updatedMagicItem.setUrl(MAGIC_URL + updatedMagicItem.getIndexName());

        magicItemRepository.save(updatedMagicItem);

        return mapToDto(updatedMagicItem);
    }

    public void deleteMagicItemById(int id) {
        magicItemRepository.findById(id).ifPresentOrElse(
                magicItemRepository::delete, () -> {
                    throw new MagicItemNotFoundException(
                            String.format("Magic item with id '%d' not found.", id)
                    );
                }
        );

    }

    // Utility method
    private MagicItemDTO mapToDto(MagicItem item) {
        EquipmentCategoryDTO equip = EquipmentCategoryDTO.builder()
                .indexName(item.getEquipmentCategory().getIndexName())
                .equipmentName(item.getEquipmentCategory().getEquipmentName())
                .url(item.getEquipmentCategory().getUrl())
                .build();
        SourceBookDTO book = SourceBookDTO.builder()
                .indexName(item.getSourceBook().getIndexName())
                .sourceName(item.getSourceBook().getSourceName())
                .url(item.getSourceBook().getUrl())
                .build();
        return new MagicItemDTO(
                item.getIndexName(),
                item.getItemName(),
                item.getTopDescr(),
                item.getDescr(),
                item.getRarity(),
                equip,
                book,
                item.getUrl());
    }

    private MagicItem mapToEntity(MagicItemRequest magicItemRequest) {
        MagicItem magicItem = MagicItem.builder()
                .itemName(magicItemRequest.itemName())
                .topDescr(magicItemRequest.topDescr())
                .descr(magicItemRequest.descr())
                .rarity(magicItemRequest.rarity())
                .build();
        magicItem.setIndexName(slugify.slugify(magicItem.getItemName()));
        magicItem.setUrl(MAGIC_URL + magicItem.getIndexName());
        return magicItem;
    }

    private EquipmentCategory getEquipCategoryFromRequest(String equipName, String itemName) {
        String equipIndexName = slugify.slugify(equipName);
        return Optional.ofNullable(equipmentCategoryRepository.findByIndexName(equipIndexName))
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(
                        String.format("Cannot create or update the Magic Item '%s' because the equipment category with name '%s' was not found.",
                                itemName,
                                equipName)));
    }

    private SourceBook getSourceBookFromRequest(String sourceName, String itemName) {
        String sourceBookIndexName = slugify.slugify(sourceName);
        return Optional.ofNullable(sourceBookRepository.findByIndexName(sourceBookIndexName))
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        String.format("Cannot create or update the Magic Item '%s' because the source book with name '%s' not found",
                                itemName,
                                sourceName)));
    }

    private void isMagicItemDuplicated(MagicItemRequest magicItemRequest) {
        if(Optional.ofNullable(magicItemRepository.findByIndexName(slugify.slugify(magicItemRequest.itemName()))).isPresent()) {
            throw new IntegrityConstraintViolationException(
                    String.format(
                            "Duplicated magic item. Cannot create or update because the item '%s' already exists.",
                            magicItemRequest.itemName()));
        }
    }

    private void isMagicItemHaveValidRarity(String itemRarity) {
        boolean isValid = false;
        for(Rarity rarity : Rarity.values()) {
            if(rarity.name().equalsIgnoreCase(itemRarity)) {
                isValid = true;
                break;
            }
        }
        if(!isValid) {
            throw new RarityEnumInvalidException(String.format("Rarity '%s' is invalid. Use a valid rarity.", itemRarity));
        }
    }
}
