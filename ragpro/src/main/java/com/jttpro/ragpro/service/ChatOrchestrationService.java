package com.jttpro.ragpro.service;

import com.jttpro.ragpro.domain.ChatMessage;
import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.dto.ArchitectureSnapshot;
import com.jttpro.ragpro.dto.ChatRequest;
import com.jttpro.ragpro.dto.ChatResponse;
import com.jttpro.ragpro.integration.llm.LlmOrchestrationService;
import com.jttpro.ragpro.integration.mysql.MysqlPersistenceFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatOrchestrationService {

    private final ShortTermMemoryService shortTermMemoryService;
    private final LongTermMemoryService longTermMemoryService;
    private final RetrievalService retrievalService;
    private final MysqlPersistenceFacade mysqlPersistenceFacade;
    private final LlmOrchestrationService llmOrchestrationService;

    public ChatOrchestrationService(ShortTermMemoryService shortTermMemoryService,
                                    LongTermMemoryService longTermMemoryService,
                                    RetrievalService retrievalService,
                                    MysqlPersistenceFacade mysqlPersistenceFacade,
                                    LlmOrchestrationService llmOrchestrationService) {
        this.shortTermMemoryService = shortTermMemoryService;
        this.longTermMemoryService = longTermMemoryService;
        this.retrievalService = retrievalService;
        this.mysqlPersistenceFacade = mysqlPersistenceFacade;
        this.llmOrchestrationService = llmOrchestrationService;
    }

    public ChatResponse chat(ChatRequest request) {
        shortTermMemoryService.appendUserMessage(request.sessionId(), request.question());
        mysqlPersistenceFacade.archiveMessage(request.sessionId(), request.userId(), "user", request.question());

        String rewrittenQuery = retrievalService.rewriteQuery(request.question());
        List<MemoryDocument> documents = retrievalService.retrieve(rewrittenQuery);
        List<ChatMessage> window = shortTermMemoryService.currentWindow(request.sessionId());

        if (request.persistToLongMemory()) {
            longTermMemoryService.persistSummary(request.sessionId(), request.userId(), request.question());
        }

        String answer = buildDemoAnswer(request.question(), request.userId(), window, documents, request.sessionId());
        shortTermMemoryService.appendAssistantMessage(request.sessionId(), answer);
        mysqlPersistenceFacade.archiveMessage(request.sessionId(), request.userId(), "assistant", answer);

        return new ChatResponse(
                request.sessionId(),
                rewrittenQuery,
                answer,
                shortTermMemoryService.describeStrategy(),
                longTermMemoryService.describeStrategy(),
                retrievalService.describeSteps(),
                documents.stream()
                        .map(document -> document.title() + "(" + document.source() + ")")
                        .toList()
        );
    }

    public ArchitectureSnapshot architecture() {
        return new ArchitectureSnapshot(
                "面向实验室团队协作场景的智能问答知识库系统示例项目",
                List.of(
                        "会话滑动窗口维护最近多轮消息，并同步到 Redis 缓存",
                        "对窗口内容做摘要压缩，减少上下文过长带来的噪声",
                        "短期记忆为多轮问答提供连续上下文"
                ),
                List.of(
                        "高价值对话支持主动写入长期记忆",
                        "摘要结果写入 MySQL 元数据表并同步向量库",
                        "基于语义相似度召回历史问题、偏好和经验"
                ),
                List.of(
                        "查询重写提升问题表达完整度",
                        "语义分块减少长文档噪声",
                        "关键词检索 + 向量检索混合召回",
                        "重排后组装最终回答上下文"
                ),
                List.of(
                        "围绕团队资料分散、检索低效、新人培训成本高等问题设计",
                        "分层结构清晰，便于课程展示和面试讲解",
                        "接口返回包含检索步骤与引用来源，方便说明系统链路"
                )
        );
    }

    private String buildDemoAnswer(String question,
                                   String userId,
                                   List<ChatMessage> window,
                                   List<MemoryDocument> documents,
                                   String sessionId) {
        String recentContext = window.stream()
                .map(ChatMessage::content)
                .limit(2)
                .reduce((left, right) -> left + " | " + right)
                .orElse("暂无上下文");

        String recallSummary = documents.stream()
                .limit(3)
                .map(MemoryDocument::title)
                .reduce((left, right) -> left + "、" + right)
                .orElse("未命中知识片段");

        String userPreference = mysqlPersistenceFacade.loadUserPreference(userId);
        List<String> contexts = List.of(
                "会话上下文：" + recentContext,
                "召回结果：" + recallSummary,
                "用户偏好：" + userPreference,
                "会话摘要占位：" + llmOrchestrationService.summarize(sessionId, shortTermMemoryService.cachedWindow(sessionId))
        );
        return llmOrchestrationService.generate(question, contexts);
    }
}
