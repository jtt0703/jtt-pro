package com.jttpro.ragpro.dto;

import java.util.List;

public record ChatResponse(
        String sessionId,
        String rewrittenQuery,
        String answer,
        String shortMemoryStrategy,
        String longMemoryStrategy,
        List<String> retrievalSteps,
        List<String> references
) {
}
