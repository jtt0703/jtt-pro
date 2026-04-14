package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.entity.ConversationArchiveEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ConversationArchiveRepository {

    public void save(String sessionId, String userId, String role, String content) {
        // placeholder for mysql archive insert
    }

    public List<ConversationArchiveEntity> findBySessionId(String sessionId) {
        return List.of(
                new ConversationArchiveEntity(
                        1L,
                        sessionId,
                        "user-001",
                        "user",
                        "如何构建企业级 RAG 系统？",
                        "chat",
                        LocalDateTime.now().minusMinutes(20)
                )
        );
    }
}
