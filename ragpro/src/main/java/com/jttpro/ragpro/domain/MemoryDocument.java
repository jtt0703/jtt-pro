package com.jttpro.ragpro.domain;

import java.util.List;

public record MemoryDocument(
        String id,
        String source,
        String title,
        String content,
        List<String> keywords,
        double vectorScore,
        double bm25Score
) {
}
