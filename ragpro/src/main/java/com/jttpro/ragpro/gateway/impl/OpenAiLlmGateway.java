package com.jttpro.ragpro.gateway.impl;

import com.jttpro.ragpro.gateway.LlmGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenAiLlmGateway implements LlmGateway {

    @Override
    public String rewriteQuery(String question) {
        return "[llm-rewrite] 围绕智能知识库、用户记忆和混合检索优化的问题：" + question;
    }

    @Override
    public String summarizeConversation(String sessionId, List<String> conversationWindow) {
        return "会话" + sessionId + "的摘要占位：" + String.join("；", conversationWindow);
    }

    @Override
    public String generateAnswer(String question, List<String> contextBlocks) {
        return "LLM 生成回答占位：针对问题【" + question + "】结合上下文【" + String.join(" | ", contextBlocks) + "】输出最终答案。";
    }
}
