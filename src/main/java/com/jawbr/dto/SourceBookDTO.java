package com.jawbr.dto;

import lombok.Builder;

@Builder
public record SourceBookDTO(
        String indexName,
        String sourceName,
        String url
) {
}
