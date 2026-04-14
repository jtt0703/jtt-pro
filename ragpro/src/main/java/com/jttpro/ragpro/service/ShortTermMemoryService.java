package com.jttpro.ragpro.service;

import com.jttpro.ragpro.domain.ChatMessage;
import com.jttpro.ragpro.integration.redis.RedisSessionCacheService;
import com.jttpro.ragpro.repository.ShortTermMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortTermMemoryService {

    private final ShortTermMemoryRepository repository;
    private final RedisSessionCacheService redisSessionCacheService;
    private final int windowSize;

    public ShortTermMemoryService(ShortTermMemoryRepository repository,
                                  RedisSessionCacheService redisSessionCacheService,
                                  @Value("${rag.short-memory.window-size:12}") int windowSize) {
        this.repository = repository;
        this.redisSessionCacheService = redisSessionCacheService;
        this.windowSize = windowSize;
    }

    public void appendUserMessage(String sessionId, String question) {
        repository.append(sessionId, "user", question);
        syncSessionContext(sessionId);
    }

    public void appendAssistantMessage(String sessionId, String answer) {
        repository.append(sessionId, "assistant", answer);
        syncSessionContext(sessionId);
    }

    public List<ChatMessage> currentWindow(String sessionId) {
        return repository.recentWindow(sessionId, windowSize);
    }

    public List<String> currentWindowTexts(String sessionId) {
        return currentWindow(sessionId).stream()
                .map(ChatMessage::content)
                .toList();
    }

    public List<String> cachedWindow(String sessionId) {
        return redisSessionCacheService.loadSessionContext(sessionId);
    }

    public String describeStrategy() {
        return "滑动窗口 + 摘要压缩 + Redis 存储，保障多轮对话上下文完整与即时连贯。";
    }

    private void syncSessionContext(String sessionId) {
        redisSessionCacheService.refreshSessionContext(sessionId, currentWindowTexts(sessionId));
    }
}
