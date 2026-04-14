package com.jttpro.ragpro.entity;

import java.time.LocalDateTime;

public record UserProfileEntity(
        Long id,
        String userId,
        String userName,
        String preferredTopics,
        String preferenceTags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
