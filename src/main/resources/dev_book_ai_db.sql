CREATE TABLE conversations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(255),
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    INDEX idx_user_id (user_id)
);

CREATE TABLE messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    conversation_id BIGINT NOT NULL,
    sender ENUM('user', 'assistant', 'system') NOT NULL,
    content TEXT,
    content_type VARCHAR(50) DEFAULT 'text',
    is_edited BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_conversation_id (conversation_id),
    FOREIGN KEY (conversation_id) REFERENCES conversations(id)
);

CREATE TABLE ai_responses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
    model_name VARCHAR(100),
    prompt_tokens INT,
    completion_tokens INT,
    total_tokens INT,
    response_time_ms INT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_message_id (message_id),
    FOREIGN KEY (message_id) REFERENCES messages(id)
);

CREATE TABLE message_metadata (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
    temperature DECIMAL(3,2),
    max_tokens INT,
    top_p DECIMAL(3,2),
    metadata JSON,

    FOREIGN KEY (message_id) REFERENCES messages(id)
);

CREATE TABLE usage_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    conversation_id BIGINT,
    model_name VARCHAR(50),
    prompt_tokens INT,
    completion_tokens INT,
    total_tokens INT,
    cost DECIMAL(10,4),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_user_id (user_id),
    INDEX idx_conversation_id (conversation_id)
);

CREATE TABLE attachments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
    file_url TEXT,
    file_type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (message_id) REFERENCES messages(id)
);

CREATE TABLE embeddings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message_id BIGINT,
    embedding JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_messages_conversation_created 
ON messages(conversation_id, created_at);

CREATE INDEX idx_conversations_user_created 
ON conversations(user_id, created_at);