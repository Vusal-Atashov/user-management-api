package org.example.userapi.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserRequest(
        String username,
        Integer age,
        LocalDateTime createdAt
) {
}