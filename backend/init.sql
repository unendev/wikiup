-- 创建wikiup数据库
CREATE DATABASE IF NOT EXISTS wikiup DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用wikiup数据库
USE wikiup;

-- 注意：表结构将由JPA自动创建（spring.jpa.hibernate.ddl-auto=update）
-- 以下是参考的表结构示例，实际表会根据Entity类自动生成

-- 如果需要手动创建，可以参考以下结构：
-- CREATE TABLE IF NOT EXISTS document (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     title VARCHAR(255) NOT NULL,
--     content TEXT,
--     file_path VARCHAR(500),
--     status VARCHAR(50),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE TABLE IF NOT EXISTS chunk (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     document_id BIGINT,
--     content TEXT NOT NULL,
--     chunk_index INT,
--     metadata JSON,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (document_id) REFERENCES document(id) ON DELETE CASCADE
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE TABLE IF NOT EXISTS embedding (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     chunk_id BIGINT,
--     vector_data TEXT,
--     dimension INT,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (chunk_id) REFERENCES chunk(id) ON DELETE CASCADE
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- CREATE INDEX idx_document_status ON document(status);
-- CREATE INDEX idx_chunk_document_id ON chunk(document_id);
-- CREATE INDEX idx_embedding_chunk_id ON embedding(chunk_id);

