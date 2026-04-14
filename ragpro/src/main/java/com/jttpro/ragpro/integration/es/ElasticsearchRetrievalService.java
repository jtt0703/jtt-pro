package com.jttpro.ragpro.integration.es;

import com.jttpro.ragpro.domain.MemoryDocument;
import com.jttpro.ragpro.gateway.ElasticsearchGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticsearchRetrievalService {

    private final ElasticsearchGateway elasticsearchGateway;

    public ElasticsearchRetrievalService(ElasticsearchGateway elasticsearchGateway) {
        this.elasticsearchGateway = elasticsearchGateway;
    }

    public List<MemoryDocument> retrieveByBm25(String query, int topK) {
        return elasticsearchGateway.searchByBm25(query, topK);
    }
}
