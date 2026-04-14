package com.jttpro.ragpro.service;

import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.integration.es.ElasticsearchRetrievalService;
import com.jttpro.ragpro.integration.llm.LlmOrchestrationService;
import com.jttpro.ragpro.integration.vector.VectorMemoryService;
import com.jttpro.ragpro.repository.LongTermMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RetrievalService {

    private final LongTermMemoryRepository repository;
    private final ElasticsearchRetrievalService elasticsearchRetrievalService;
    private final VectorMemoryService vectorMemoryService;
    private final LlmOrchestrationService llmOrchestrationService;
    private final boolean enableQueryRewrite;
    private final boolean enableBm25;
    private final boolean enableVector;
    private final int topK;

    public RetrievalService(LongTermMemoryRepository repository,
                            ElasticsearchRetrievalService elasticsearchRetrievalService,
                            VectorMemoryService vectorMemoryService,
                            LlmOrchestrationService llmOrchestrationService,
                            @Value("${rag.retrieval.enable-query-rewrite:true}") boolean enableQueryRewrite,
                            @Value("${rag.retrieval.enable-bm25:true}") boolean enableBm25,
                            @Value("${rag.retrieval.enable-vector:true}") boolean enableVector,
                            @Value("${rag.long-memory.top-k:5}") int topK) {
        this.repository = repository;
        this.elasticsearchRetrievalService = elasticsearchRetrievalService;
        this.vectorMemoryService = vectorMemoryService;
        this.llmOrchestrationService = llmOrchestrationService;
        this.enableQueryRewrite = enableQueryRewrite;
        this.enableBm25 = enableBm25;
        this.enableVector = enableVector;
        this.topK = topK;
    }

    public String rewriteQuery(String question) {
        if (!enableQueryRewrite) {
            return question;
        }
        return llmOrchestrationService.rewrite(question);
    }

    public List<MemoryDocument> retrieve(String rewrittenQuery) {
        List<MemoryDocument> merged = new ArrayList<>();
        if (enableVector) {
            merged.addAll(vectorMemoryService.semanticSearch(rewrittenQuery, topK));
            merged.addAll(repository.searchVector(rewrittenQuery));
        }
        if (enableBm25) {
            merged.addAll(elasticsearchRetrievalService.retrieveByBm25(rewrittenQuery, topK));
            merged.addAll(repository.searchKeyword(rewrittenQuery));
        }
        return merged.stream()
                .sorted(Comparator.comparingDouble(this::hybridScore).reversed())
                .limit(topK)
                .toList();
    }

    public List<String> describeSteps() {
        return List.of(
                "查询重写：通过 LLM 补全业务语义，提升召回意图清晰度",
                "语义分块：按主题对知识片段切分，降低噪声干扰",
                "混合检索：并行执行 Elasticsearch BM25 与向量库检索",
                "结果重排：根据向量分与关键词分生成最终上下文"
        );
    }

    public String describeStrategy() {
        return "查询重写 + 语义分块 + 关键词检索与向量检索混合召回，提升知识检索准确率。";
    }

    private double hybridScore(MemoryDocument document) {
        return document.vectorScore() * 10 + document.bm25Score();
    }
}
