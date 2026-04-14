package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.entity.LongMemoryRecordEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LongMemoryRecordRepository {

    public void save(String sessionId, String userId, String summary, String embeddingId, double importanceScore) {
        // placeholder for mysql long-memory insert
    }

    public List<LongMemoryRecordEntity> findByUserId(String userId) {
        return List.of(
                new LongMemoryRecordEntity(
                        1L,
                        "session-001",
                        userId,
                        "用户长期关注企业知识库与智能问答系统设计。",
                        "embedding-001",
                        0.91,
                        LocalDateTime.now().minusDays(2)
                )
        );
    }
}
