package com.jawbr.controller;

import com.jawbr.dto.SourceBookDTO;
import com.jawbr.dto.request.SourceBookRequest;
import com.jawbr.service.SourceBookService;
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
@RequestMapping("/api/source-books")
public class SourceBookController {

    private final SourceBookService sourceBookService;

    public SourceBookController(SourceBookService sourceBookService) {
        this.sourceBookService = sourceBookService;
    }

    @GetMapping
    public List<SourceBookDTO> findAllSourceBooks() {
        return sourceBookService.findAllSourceBooks();
    }

    @GetMapping("/{indexName}")
    public SourceBookDTO findSourceBookByIndexName(@PathVariable String indexName) {
        return sourceBookService.getSourceBookByIndexName(indexName);
    }

    // All endpoints below can only be accessed by ADMIN role

    @GetMapping("/id/{id}")
    public SourceBookDTO findSourceBookById(@PathVariable int id) {
        return sourceBookService.getSourceBookById(id);
    }

    @PostMapping
    public ResponseEntity<SourceBookDTO> createSourceBook(
            @Valid @RequestBody SourceBookRequest sourceBookRequest) {
        SourceBookDTO response =
                sourceBookService.createSourceBook(sourceBookRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public SourceBookDTO updateSourceBook(
            @PathVariable int id, @RequestBody SourceBookRequest sourceBookRequest) {
        return sourceBookService.updateSourceBook(id, sourceBookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSourceBookById(@PathVariable int id) {
        sourceBookService.deleteSourceBookById(id);
        return ResponseEntity.noContent().build();
    }
}
