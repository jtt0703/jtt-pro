package com.jttpro.ragpro.gateway.impl;

import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.gateway.VectorStoreGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MilvusVectorStoreGateway implements VectorStoreGateway {

    @Override
    public void upsert(String userId, String memoryId, String content) {
        // placeholder for vector upsert
    }

    @Override
    public List<MemoryDocument> search(String query, int topK) {
        return List.of(
                new MemoryDocument(
                        "milvus-001",
                        "milvus-vector-store",
                        "用户长期兴趣向量召回",
                        "从向量库中召回与当前问题语义相似的历史长期记忆摘要。",
                        List.of("Milvus", "embedding", "long-memory"),
                        0.93,
                        0.0
                )
        );
    }
}
