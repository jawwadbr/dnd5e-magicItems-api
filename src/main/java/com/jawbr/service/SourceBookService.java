package com.jawbr.service;

import com.github.slugify.Slugify;
import com.jawbr.dto.SourceBookDTO;
import com.jawbr.dto.request.SourceBookRequest;
import com.jawbr.entity.SourceBook;
import com.jawbr.exception.IntegrityConstraintViolationException;
import com.jawbr.exception.SourceBookExceptionNotFound;
import com.jawbr.repository.SourceBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class SourceBookService {

    private final SourceBookRepository sourceBookRepository;
    private final Slugify slugify;

    private static final String BOOK_URL = "/api/source-books/";

    public SourceBookService(SourceBookRepository sourceBookRepository) {
        this.sourceBookRepository = sourceBookRepository;
        this.slugify = Slugify.builder().build();
    }

    public Page<SourceBookDTO> findAllSourceBooks(Integer page, Integer pageSize, String sortBy) {
        // limits pageSize to 200
        pageSize = Math.min(Optional.ofNullable(pageSize).orElse(15), 200);
        sortBy = Optional.ofNullable(sortBy)
                .filter(s -> !s.isEmpty())
                .orElse("id");
        Page<SourceBook> bookList = Optional.of(sourceBookRepository.findAll(
                        PageRequest.of(
                                Optional.ofNullable(page).orElse(0),
                                pageSize,
                                Sort.Direction.ASC,
                                sortBy)))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        "No source books found."));
        return bookList.map(this::mapToDto);
    }

    public SourceBookDTO findSourceBookByIndexName(String indexName) {
        SourceBook book = Optional.ofNullable(sourceBookRepository.findByIndexName(indexName))
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        String.format("Source book with index name '%s' not found", indexName)));
        return mapToDto(book);
    }

    public SourceBookDTO findSourceBookById(int id) {
        SourceBook book = sourceBookRepository.findById(id)
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        String.format("Source book with index name '%d' not found", id)));
        return mapToDto(book);
    }

    public SourceBookDTO createSourceBook(SourceBookRequest sourceBookRequest) {
        isSourceBookDuplicated(sourceBookRequest);
        SourceBook newBook = sourceBookRepository.save(mapToEntity(sourceBookRequest));
        return mapToDto(newBook);
    }

    public SourceBookDTO updateSourceBook(int id, SourceBookRequest sourceBookRequest) {
        SourceBook book = sourceBookRepository.findById(id)
                .orElseThrow(() -> new SourceBookExceptionNotFound(
                        String.format("Source book with id '%d' not found.", id)
                ));

        isSourceBookDuplicated(sourceBookRequest);

        final String sourceName = StringUtils.hasText(
                sourceBookRequest.sourceName()) ? sourceBookRequest.sourceName() : book.getSourceName();

        SourceBook updatedSourceBook = SourceBook.builder()
                .id(book.getId())
                .sourceName(sourceName)
                .indexName(slugify.slugify(sourceName))
                .build();
        updatedSourceBook.setUrl(BOOK_URL + updatedSourceBook.getIndexName());

        sourceBookRepository.save(updatedSourceBook);

        return mapToDto(updatedSourceBook);
    }

    public void deleteSourceBookById(int id) {
        sourceBookRepository.findById(id).ifPresentOrElse(
                sourceBookRepository::delete, () -> {
                    throw new SourceBookExceptionNotFound(
                            String.format("Source book with id '%d' not found.", id)
                    );
                }
        );

    }

    // Utility method
    private SourceBookDTO mapToDto(SourceBook sourceBook) {
        return SourceBookDTO.builder()
                .indexName(sourceBook.getIndexName())
                .sourceName(sourceBook.getSourceName())
                .url(sourceBook.getUrl()).build();
    }

    private SourceBook mapToEntity(SourceBookRequest sourceBookRequest) {
        SourceBook book = SourceBook.builder()
                .sourceName(sourceBookRequest.sourceName()).build();
        book.setIndexName(slugify.slugify(book.getSourceName()));
        book.setUrl(BOOK_URL + book.getIndexName());
        return book;
    }

    private void isSourceBookDuplicated(SourceBookRequest sourceBookRequest) {
        if(Optional.ofNullable(sourceBookRepository.findByIndexName(slugify.slugify(sourceBookRequest.sourceName()))).isPresent()) {
            throw new IntegrityConstraintViolationException(
                    String.format(
                            "Duplicated source book. The source '%s' already exists.",
                            sourceBookRequest.sourceName()));
        }
    }
}
