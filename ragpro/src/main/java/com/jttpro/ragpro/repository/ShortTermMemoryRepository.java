package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.domain.ChatMessage;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortTermMemoryRepository {

    private final Map<String, List<ChatMessage>> sessionMessages = new ConcurrentHashMap<>();

    public void append(String sessionId, String role, String content) {
        sessionMessages
                .computeIfAbsent(sessionId, key -> new ArrayList<>())
                .add(new ChatMessage(sessionId, role, content, LocalDateTime.now(), List.of("conversation")));
    }

    public List<ChatMessage> recentWindow(String sessionId, int windowSize) {
        return sessionMessages.getOrDefault(sessionId, List.of())
                .stream()
                .sorted(Comparator.comparing(ChatMessage::timestamp).reversed())
                .limit(windowSize)
                .toList();
    }
}
