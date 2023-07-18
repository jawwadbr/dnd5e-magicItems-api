package com.jawbr.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record UserDTO(
        String username,
        Collection<AuthRoleDTO> roles,
        boolean active,
        LocalDateTime createdAt
) {
}
