package com.jttpro.ragpro.gateway;

import com.jttpro.ragpro.domain.MemoryDocument;

import java.util.List;

public interface VectorStoreGateway {

    void upsert(String userId, String memoryId, String content);

    List<MemoryDocument> search(String query, int topK);
}
