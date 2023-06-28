package com.jawbr.service;

import com.jawbr.dto.SourceBookDTO;
import com.jawbr.entity.SourceBook;
import com.jawbr.exception.SourceBookExceptionNotFound;
import com.jawbr.repository.SourceBookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SourceBookService {

    private final SourceBookRepository sourceBookRepository;


    public SourceBookService(SourceBookRepository sourceBookRepository) {
        this.sourceBookRepository = sourceBookRepository;
    }

    // TODO findAll, findById, save, update, delete

    public SourceBookDTO getSourceBookByIndexName(String indexName) {
        SourceBook book = Optional.ofNullable(sourceBookRepository.findByIndexName(indexName))
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        String.format("Source book with index name %s not found", indexName)));
        return mapToDto(book);
    }

    private SourceBookDTO mapToDto(SourceBook sourceBook) {
        return SourceBookDTO.builder()
                .indexName(sourceBook.getIndexName())
                .sourceName(sourceBook.getSourceName())
                .url(sourceBook.getSourceName()).build();
    }
}
