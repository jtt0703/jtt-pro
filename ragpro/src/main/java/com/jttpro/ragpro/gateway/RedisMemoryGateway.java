package com.jttpro.ragpro.gateway;

import java.util.List;

public interface RedisMemoryGateway {

    void cacheSessionWindow(String sessionId, List<String> messages);

    List<String> loadSessionWindow(String sessionId);
}
