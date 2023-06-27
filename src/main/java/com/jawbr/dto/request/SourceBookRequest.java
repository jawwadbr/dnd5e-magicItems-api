package com.jawbr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SourceBookRequest(
        @NotBlank(message = "The source book cannot be empty") String sourceName
) {
}
