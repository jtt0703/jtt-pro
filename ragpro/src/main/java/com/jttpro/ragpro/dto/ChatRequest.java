package com.jttpro.ragpro.dto;

public record ChatRequest(
        String sessionId,
        String userId,
        String question,
        boolean persistToLongMemory
) {
}
