package com.jttpro.ragpro.service;

import com.jttpro.ragpro.domain.MemorySummary;
import com.jttpro.ragpro.gateway.EmbeddingGateway;
import com.jttpro.ragpro.integration.mysql.MysqlPersistenceFacade;
import com.jttpro.ragpro.integration.vector.VectorMemoryService;
import com.jttpro.ragpro.repository.MemorySummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LongTermMemoryService {

    private final MemorySummaryRepository summaryRepository;
    private final EmbeddingGateway embeddingGateway;
    private final VectorMemoryService vectorMemoryService;
    private final MysqlPersistenceFacade mysqlPersistenceFacade;

    public LongTermMemoryService(MemorySummaryRepository summaryRepository,
                                 EmbeddingGateway embeddingGateway,
                                 VectorMemoryService vectorMemoryService,
                                 MysqlPersistenceFacade mysqlPersistenceFacade) {
        this.summaryRepository = summaryRepository;
        this.embeddingGateway = embeddingGateway;
        this.vectorMemoryService = vectorMemoryService;
        this.mysqlPersistenceFacade = mysqlPersistenceFacade;
    }

    public void persistSummary(String sessionId, String userId, String question) {
        MemorySummary summary = new MemorySummary(
                sessionId,
                "基于高价值会话生成用户长期兴趣摘要：" + question,
                List.of("主动写入", "语义记忆", "个性化"),
                0.92
        );
        summaryRepository.save(summary);

        String embeddingId = "embedding-" + UUID.randomUUID();
        embeddingGateway.embed(summary.summary());
        vectorMemoryService.saveMemoryVector(userId, embeddingId, summary.summary());
        mysqlPersistenceFacade.persistLongMemory(sessionId, userId, summary.summary(), embeddingId, summary.importanceScore());
    }

    public List<MemorySummary> listSummaries() {
        return summaryRepository.findAll();
    }

    public String describeStrategy() {
        return "主动写入 + 向量化存储 + MySQL 元数据保存 + 语义相似度召回，构建精准可控的长期记忆机制。";
    }
}
