package com.jttpro.ragpro.dto;

import java.util.List;

public record ArchitectureSnapshot(
        String projectPositioning,
        List<String> shortTermMemoryModules,
        List<String> longTermMemoryModules,
        List<String> ragRecallModules,
        List<String> engineeringHighlights
) {
}
