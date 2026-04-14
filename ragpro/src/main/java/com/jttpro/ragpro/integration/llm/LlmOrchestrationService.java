package com.jttpro.ragpro.integration.llm;

import com.jttpro.ragpro.gateway.LlmGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LlmOrchestrationService {

    private final LlmGateway llmGateway;

    public LlmOrchestrationService(LlmGateway llmGateway) {
        this.llmGateway = llmGateway;
    }

    public String rewrite(String question) {
        return llmGateway.rewriteQuery(question);
    }

    public String summarize(String sessionId, List<String> messages) {
        return llmGateway.summarizeConversation(sessionId, messages);
    }

    public String generate(String question, List<String> contexts) {
        return llmGateway.generateAnswer(question, contexts);
    }
}
