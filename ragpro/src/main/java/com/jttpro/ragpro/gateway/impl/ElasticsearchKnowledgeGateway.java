package com.jttpro.ragpro.gateway.impl;

import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.gateway.ElasticsearchGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticsearchKnowledgeGateway implements ElasticsearchGateway {

    @Override
    public List<MemoryDocument> searchByBm25(String query, int topK) {
        return List.of(
                new MemoryDocument(
                        "es-001",
                        "elasticsearch",
                        "BM25 知识片段召回",
                        "通过 Elasticsearch 对知识分块执行关键词检索，提升精确匹配能力。",
                        List.of("Elasticsearch", "BM25", "RAG"),
                        0.0,
                        8.8
                )
        );
    }
}
