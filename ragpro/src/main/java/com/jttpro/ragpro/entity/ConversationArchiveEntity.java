package com.jttpro.ragpro.entity;

import java.time.LocalDateTime;

public record ConversationArchiveEntity(
        Long id,
        String sessionId,
        String userId,
        String role,
        String content,
        String messageType,
        LocalDateTime createdAt
) {
}
