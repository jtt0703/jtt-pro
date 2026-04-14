package com.jttpro.ragpro.controller;

import com.jttpro.ragpro.dto.ArchitectureSnapshot;
import com.jttpro.ragpro.dto.ChatRequest;
import com.jttpro.ragpro.dto.ChatResponse;
import com.jttpro.ragpro.service.ChatOrchestrationService;
import com.jttpro.ragpro.service.LongTermMemoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rag")
public class RagDemoController {

    private final ChatOrchestrationService chatOrchestrationService;
    private final LongTermMemoryService longTermMemoryService;

    public RagDemoController(ChatOrchestrationService chatOrchestrationService,
                             LongTermMemoryService longTermMemoryService) {
        this.chatOrchestrationService = chatOrchestrationService;
        this.longTermMemoryService = longTermMemoryService;
    }

    @GetMapping("/architecture")
    public ArchitectureSnapshot architecture() {
        return chatOrchestrationService.architecture();
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatOrchestrationService.chat(request);
    }

    @GetMapping("/long-memory")
    public List<Map<String, Object>> longMemory() {
        return longTermMemoryService.listSummaries().stream()
                .map(summary -> Map.of(
                        "sessionId", summary.sessionId(),
                        "summary", summary.summary(),
                        "keywords", summary.keywords(),
                        "importanceScore", summary.importanceScore()
                ))
                .toList();
    }
}
