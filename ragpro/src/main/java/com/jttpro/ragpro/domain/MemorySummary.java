package com.jttpro.ragpro.domain;

import java.util.List;

public record MemorySummary(
        String sessionId,
        String summary,
        List<String> keywords,
        double importanceScore
) {
}
