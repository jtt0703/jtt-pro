package com.jttpro.ragpro.gateway;

import java.util.List;

public interface LlmGateway {

    String rewriteQuery(String question);

    String summarizeConversation(String sessionId, List<String> conversationWindow);

    String generateAnswer(String question, List<String> contextBlocks);
}
