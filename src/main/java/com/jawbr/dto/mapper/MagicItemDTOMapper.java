package com.jawbr.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.jawbr.dto.MagicItemDTO;
import com.jawbr.entity.MagicItem;
import com.jawbr.utils.SplitDescr;

@Service
public class MagicItemDTOMapper implements Function<MagicItem, MagicItemDTO>{

	@Override
	public MagicItemDTO apply(MagicItem magicItem) {
		SplitDescr.splitDescr(magicItem);
		return new MagicItemDTO(
				magicItem.getIndexName(), 
				magicItem.getItemName(),
				magicItem.getDescription(),
				magicItem.getRarity(), 
				magicItem.getUrl(), 
				magicItem.getEquipmentCategory(), 
				magicItem.getSourceBook()
				);
	}

	public MagicItemDTO mapToDto(MagicItem item) {
		SplitDescr.splitDescr(item);
		MagicItemDTO itemDTO = new MagicItemDTO(
				item.getIndexName(), 
				item.getItemName(), 
				item.getDescription(),
				item.getRarity(), 
				item.getUrl(), 
				item.getEquipmentCategory(),
				item.getSourceBook());
		
		return itemDTO;
	}

	public List<MagicItemDTO> mapToDto(List<MagicItem> items) {
		SplitDescr.splitDescr(items);
		
		List<MagicItemDTO> results = new ArrayList<>();
		
		for (MagicItem item : items) {
			MagicItemDTO dto = new MagicItemDTO(
					item.getIndexName(), 
					item.getItemName(), 
					item.getDescription(),
					item.getRarity(), 
					item.getUrl(), 
					item.getEquipmentCategory(),
					item.getSourceBook());
			results.add(dto);
		}
		
		return results;
	}

	public MagicItem toEntity(MagicItemDTO magicItem) {
		
		MagicItem magicItemEntity = new MagicItem(magicItem.indexName(), 
				magicItem.itemName(), 
				magicItem.description(),
				magicItem.rarity(), 
				magicItem.url());
		
		return magicItemEntity;
	}

}
