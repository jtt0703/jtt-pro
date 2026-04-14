package com.jttpro.ragpro.mapper;

import com.jttpro.ragpro.domain.MemorySummary;
import com.jttpro.ragpro.entity.LongMemoryRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class LongMemoryRecordMapper {

    public LongMemoryRecordEntity toEntity(String sessionId, String userId, MemorySummary summary, String embeddingId) {
        return new LongMemoryRecordEntity(
                null,
                sessionId,
                userId,
                summary.summary(),
                embeddingId,
                summary.importanceScore(),
                null
        );
    }
}
