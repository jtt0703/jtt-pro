package com.jttpro.ragpro.integration.redis;

import com.jttpro.ragpro.gateway.RedisMemoryGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisSessionCacheService {

    private final RedisMemoryGateway redisMemoryGateway;

    public RedisSessionCacheService(RedisMemoryGateway redisMemoryGateway) {
        this.redisMemoryGateway = redisMemoryGateway;
    }

    public void refreshSessionContext(String sessionId, List<String> messages) {
        redisMemoryGateway.cacheSessionWindow(sessionId, messages);
    }

    public List<String> loadSessionContext(String sessionId) {
        return redisMemoryGateway.loadSessionWindow(sessionId);
    }
}
