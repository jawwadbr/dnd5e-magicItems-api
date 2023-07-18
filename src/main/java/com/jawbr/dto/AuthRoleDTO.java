package com.jawbr.dto;

import lombok.Builder;

@Builder
public record AuthRoleDTO(
        String role
) {
}
