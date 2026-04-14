package com.jttpro.ragpro.integration.vector;

import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.gateway.VectorStoreGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorMemoryService {

    private final VectorStoreGateway vectorStoreGateway;

    public VectorMemoryService(VectorStoreGateway vectorStoreGateway) {
        this.vectorStoreGateway = vectorStoreGateway;
    }

    public void saveMemoryVector(String userId, String memoryId, String content) {
        vectorStoreGateway.upsert(userId, memoryId, content);
    }

    public List<MemoryDocument> semanticSearch(String query, int topK) {
        return vectorStoreGateway.search(query, topK);
    }
}
