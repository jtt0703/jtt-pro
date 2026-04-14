package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.entity.KnowledgeChunkEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class KnowledgeChunkRepository {

    public List<KnowledgeChunkEntity> findByDocumentId(String documentId) {
        return List.of(
                new KnowledgeChunkEntity(
                        1L,
                        documentId,
                        "RAG 知识库召回策略",
                        "使用 BM25 与向量检索混合召回，以提升知识命中率。",
                        "RAG,BM25,向量检索",
                        0,
                        LocalDateTime.now().minusDays(7)
                )
        );
    }
}
