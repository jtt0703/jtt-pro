package com.jttpro.ragpro.gateway.impl;

import com.jttpro.ragpro.gateway.EmbeddingGateway;
import org.springframework.stereotype.Component;

@Component
public class OpenAiEmbeddingGateway implements EmbeddingGateway {

    @Override
    public String embed(String content) {
        return "embedding-vector-placeholder-for:" + content;
    }
}
