package com.jttpro.ragpro.gateway.impl;

import com.jttpro.ragpro.gateway.RedisMemoryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RedisSessionMemoryGateway implements RedisMemoryGateway {

    private final Map<String, List<String>> cache = new ConcurrentHashMap<>();

    @Override
    public void cacheSessionWindow(String sessionId, List<String> messages) {
        cache.put(sessionId, List.copyOf(messages));
    }

    @Override
    public List<String> loadSessionWindow(String sessionId) {
        return cache.getOrDefault(sessionId, List.of());
    }
}
