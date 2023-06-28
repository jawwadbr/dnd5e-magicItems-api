package com.jawbr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SourceBookRequest(
        @NotBlank(message = "Source book name cannot be empty") String sourceName
) {
}
