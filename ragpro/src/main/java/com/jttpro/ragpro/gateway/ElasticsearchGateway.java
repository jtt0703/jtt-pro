package com.jttpro.ragpro.gateway;

import com.jttpro.ragpro.domain.MemoryDocument;

import java.util.List;

public interface ElasticsearchGateway {

    List<MemoryDocument> searchByBm25(String query, int topK);
}
