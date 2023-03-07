package com.jawbr.dto.mapper;

import java.util.function.Function;

import com.jawbr.dto.SourceBookDTO;
import com.jawbr.entity.SourceBook;

public class SourceBookDTOMapper implements Function<SourceBook, SourceBookDTO> {

	@Override
	public SourceBookDTO apply(SourceBook sourceBook) {
		return new SourceBookDTO(sourceBook.getSourceName());
	}

}
