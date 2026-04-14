package com.jttpro.ragpro.entity;

import java.time.LocalDateTime;

public record LongMemoryRecordEntity(
        Long id,
        String sessionId,
        String userId,
        String summary,
        String embeddingId,
        Double importanceScore,
        LocalDateTime createdAt
) {
}
