package com.jttpro.ragpro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rag")
public class RagProperties {

    private final ShortMemory shortMemory = new ShortMemory();
    private final LongMemory longMemory = new LongMemory();
    private final Retrieval retrieval = new Retrieval();
    private final Llm llm = new Llm();

    public ShortMemory getShortMemory() {
        return shortMemory;
    }

    public LongMemory getLongMemory() {
        return longMemory;
    }

    public Retrieval getRetrieval() {
        return retrieval;
    }

    public Llm getLlm() {
        return llm;
    }

    public static class ShortMemory {
        private int windowSize;
        private int summaryThreshold;
        private String redisKeyPrefix;
        private int redisTtlMinutes;

        public int getWindowSize() {
            return windowSize;
        }

        public void setWindowSize(int windowSize) {
            this.windowSize = windowSize;
        }

        public int getSummaryThreshold() {
            return summaryThreshold;
        }

        public void setSummaryThreshold(int summaryThreshold) {
            this.summaryThreshold = summaryThreshold;
        }

        public String getRedisKeyPrefix() {
            return redisKeyPrefix;
        }

        public void setRedisKeyPrefix(String redisKeyPrefix) {
            this.redisKeyPrefix = redisKeyPrefix;
        }

        public int getRedisTtlMinutes() {
            return redisTtlMinutes;
        }

        public void setRedisTtlMinutes(int redisTtlMinutes) {
            this.redisTtlMinutes = redisTtlMinutes;
        }
    }

    public static class LongMemory {
        private int topK;
        private double similarityThreshold;
        private boolean autoWrite;
        private String embeddingModel;
        private String vectorStore;

        public int getTopK() {
            return topK;
        }

        public void setTopK(int topK) {
            this.topK = topK;
        }

        public double getSimilarityThreshold() {
            return similarityThreshold;
        }

        public void setSimilarityThreshold(double similarityThreshold) {
            this.similarityThreshold = similarityThreshold;
        }

        public boolean isAutoWrite() {
            return autoWrite;
        }

        public void setAutoWrite(boolean autoWrite) {
            this.autoWrite = autoWrite;
        }

        public String getEmbeddingModel() {
            return embeddingModel;
        }

        public void setEmbeddingModel(String embeddingModel) {
            this.embeddingModel = embeddingModel;
        }

        public String getVectorStore() {
            return vectorStore;
        }

        public void setVectorStore(String vectorStore) {
            this.vectorStore = vectorStore;
        }
    }

    public static class Retrieval {
        private boolean enableBm25;
        private boolean enableVector;
        private boolean enableQueryRewrite;
        private boolean enableRerank;
        private int chunkSize;
        private int chunkOverlap;
        private String indexName;

        public boolean isEnableBm25() {
            return enableBm25;
        }

        public void setEnableBm25(boolean enableBm25) {
            this.enableBm25 = enableBm25;
        }

        public boolean isEnableVector() {
            return enableVector;
        }

        public void setEnableVector(boolean enableVector) {
            this.enableVector = enableVector;
        }

        public boolean isEnableQueryRewrite() {
            return enableQueryRewrite;
        }

        public void setEnableQueryRewrite(boolean enableQueryRewrite) {
            this.enableQueryRewrite = enableQueryRewrite;
        }

        public boolean isEnableRerank() {
            return enableRerank;
        }

        public void setEnableRerank(boolean enableRerank) {
            this.enableRerank = enableRerank;
        }

        public int getChunkSize() {
            return chunkSize;
        }

        public void setChunkSize(int chunkSize) {
            this.chunkSize = chunkSize;
        }

        public int getChunkOverlap() {
            return chunkOverlap;
        }

        public void setChunkOverlap(int chunkOverlap) {
            this.chunkOverlap = chunkOverlap;
        }

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }
    }

    public static class Llm {
        private String provider;
        private String baseUrl;
        private String apiKey;
        private String chatModel;

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getChatModel() {
            return chatModel;
        }

        public void setChatModel(String chatModel) {
            this.chatModel = chatModel;
        }
    }
}
