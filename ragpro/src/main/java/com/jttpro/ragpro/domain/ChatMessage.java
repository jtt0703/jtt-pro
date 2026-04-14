package com.jttpro.ragpro.domain;

import java.time.LocalDateTime;
import java.util.List;

public record ChatMessage(
        String sessionId,
        String role,
        String content,
        LocalDateTime timestamp,
        List<String> tags
) {
}
