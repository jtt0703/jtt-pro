package com.jttpro.ragpro.entity;

import java.time.LocalDateTime;

public record KnowledgeChunkEntity(
        Long id,
        String documentId,
        String title,
        String chunkContent,
        String keywords,
        Integer chunkIndex,
        LocalDateTime createdAt
) {
}
