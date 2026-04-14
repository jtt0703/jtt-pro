package com.jttpro.ragpro.integration.mysql;

import com.jttpro.ragpro.repository.ConversationArchiveRepository;
import com.jttpro.ragpro.repository.LongMemoryRecordRepository;
import com.jttpro.ragpro.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class MysqlPersistenceFacade {

    private final UserProfileRepository userProfileRepository;
    private final ConversationArchiveRepository conversationArchiveRepository;
    private final LongMemoryRecordRepository longMemoryRecordRepository;

    public MysqlPersistenceFacade(UserProfileRepository userProfileRepository,
                                  ConversationArchiveRepository conversationArchiveRepository,
                                  LongMemoryRecordRepository longMemoryRecordRepository) {
        this.userProfileRepository = userProfileRepository;
        this.conversationArchiveRepository = conversationArchiveRepository;
        this.longMemoryRecordRepository = longMemoryRecordRepository;
    }

    public void archiveMessage(String sessionId, String userId, String role, String content) {
        conversationArchiveRepository.save(sessionId, userId, role, content);
    }

    public String loadUserPreference(String userId) {
        return userProfileRepository.findByUserId(userId).preferredTopics();
    }

    public void persistLongMemory(String sessionId, String userId, String summary, String embeddingId, double importanceScore) {
        longMemoryRecordRepository.save(sessionId, userId, summary, embeddingId, importanceScore);
    }
}
