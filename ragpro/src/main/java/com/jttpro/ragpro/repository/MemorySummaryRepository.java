package com.jttpro.ragpro.repository;

import com.jttpro.ragpro.domain.MemorySummary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MemorySummaryRepository {

    private final List<MemorySummary> summaries = new CopyOnWriteArrayList<>();

    public void save(MemorySummary summary) {
        summaries.add(summary);
    }

    public List<MemorySummary> findAll() {
        return List.copyOf(summaries);
    }
}
