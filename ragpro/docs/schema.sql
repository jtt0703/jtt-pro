-- ========================================
-- RAG Pro schema design (showcase edition)
-- ========================================

CREATE TABLE user_profile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(64) NOT NULL UNIQUE,
    user_name VARCHAR(128) NOT NULL,
    preferred_topics VARCHAR(512),
    preference_tags VARCHAR(512),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

CREATE TABLE conversation_archive (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    session_id VARCHAR(64) NOT NULL,
    user_id VARCHAR(64) NOT NULL,
    role VARCHAR(32) NOT NULL,
    content TEXT NOT NULL,
    message_type VARCHAR(32) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE long_memory_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    session_id VARCHAR(64) NOT NULL,
    user_id VARCHAR(64) NOT NULL,
    summary TEXT NOT NULL,
    embedding_id VARCHAR(128) NOT NULL,
    importance_score DECIMAL(4,2) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE knowledge_chunk (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    document_id VARCHAR(64) NOT NULL,
    title VARCHAR(256) NOT NULL,
    chunk_content TEXT NOT NULL,
    keywords VARCHAR(512),
    chunk_index INT NOT NULL,
    created_at DATETIME NOT NULL
);
