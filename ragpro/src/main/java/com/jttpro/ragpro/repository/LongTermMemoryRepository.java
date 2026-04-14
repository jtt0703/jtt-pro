package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.domain.MemoryDocument;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LongTermMemoryRepository {

    public List<MemoryDocument> searchVector(String query) {
        return List.of(
                new MemoryDocument(
                        "vec-101",
                        "vector-store",
                        "客户画像偏好摘要",
                        "通过用户历史提问、点击和反馈构建画像，用于个性化问答和推荐。",
                        List.of("画像", "偏好", "长期记忆"),
                        0.91,
                        0.0
                ),
                new MemoryDocument(
                        "vec-102",
                        "vector-store",
                        "主动写入长期记忆",
                        "将高价值对话自动摘要后写入向量库，并以语义相似度触发召回。",
                        List.of("摘要", "向量存储", "召回"),
                        0.87,
                        0.0
                )
        );
    }

    public List<MemoryDocument> searchKeyword(String query) {
        return List.of(
                new MemoryDocument(
                        "bm25-201",
                        "knowledge-base",
                        "Redis 会话短期记忆",
                        "通过滑动窗口 + Redis 缓存机制确保多轮对话上下文实时可用。",
                        List.of("Redis", "短期记忆", "滑动窗口"),
                        0.0,
                        8.6
                ),
                new MemoryDocument(
                        "bm25-202",
                        "knowledge-base",
                        "混合检索策略",
                        "结合 BM25 与向量检索，并通过查询重写与分块策略提升召回准确率。",
                        List.of("BM25", "混合检索", "查询重写"),
                        0.0,
                        8.1
                )
        );
    }
}
