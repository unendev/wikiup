# MySQL迁移指南

## 概述

本指南帮助你将项目从文件存储迁移到MySQL数据库。

## 1. 数据模型变更

### 实体类更新

所有实体类已添加JPA注解，支持MySQL持久化：

#### Document实体
- 添加了 `@Entity` 和 `@Table` 注解
- `id` 字段改为 `Long` 类型（数据库主键）
- 新增 `docId` 字段存储原有的业务ID（String类型）
- 支持一对多关联到 `Chunk`
- 元数据存储在单独的 `document_metadata` 表中

#### Chunk实体
- 添加了 `@Entity` 和 `@Table` 注解
- `id` 字段改为 `Long` 类型（数据库主键）
- 新增 `chunkId` 字段存储原有的业务ID（String类型）
- 关联到 `Document`（多对一）
- `embedding` 数组序列化为 `embeddingData` 字符串存储
- 元数据存储在单独的 `chunk_metadata` 表中

#### Embedding实体
- 添加了 `@Entity` 和 `@Table` 注解
- `id` 字段改为 `Long` 类型（数据库主键）
- `vector` 数组序列化为 `vectorData` 字符串存储

### 字段映射

| 原字段 | 新字段 | 类型变更 | 说明 |
|-------|-------|---------|------|
| Document.id | Document.id / Document.docId | String -> Long/String | id改为数据库主键，docId保留原业务ID |
| Chunk.id | Chunk.id / Chunk.chunkId | String -> Long/String | id改为数据库主键，chunkId保留原业务ID |
| Chunk.documentId | Chunk.document | String -> Document对象 | 改为JPA关联 |
| Chunk.embedding | Chunk.embeddingData | float[] -> String | 序列化存储 |
| Embedding.id | Embedding.id | String -> Long | 改为数据库主键 |
| Embedding.vector | Embedding.vectorData | float[] -> String | 序列化存储 |

## 2. Repository更新

### 新增JPA Repository接口

创建了三个新的JPA Repository接口：

1. **JpaDocumentRepository** - 继承 `JpaRepository<Document, Long>`
2. **JpaChunkRepository** - 继承 `JpaRepository<Chunk, Long>`
3. **JpaEmbeddingRepository** - 继承 `JpaRepository<Embedding, Long>`

### 使用示例

```java
@Service
public class DocumentServiceImpl {
    
    @Autowired
    private JpaDocumentRepository documentRepository;
    
    @Autowired
    private JpaChunkRepository chunkRepository;
    
    public Document saveDocument(Document document) {
        // 设置业务ID（如果需要）
        if (document.getDocId() == null) {
            document.setDocId(UUID.randomUUID().toString());
        }
        
        // 保存文档
        return documentRepository.save(document);
    }
    
    public Optional<Document> findByDocId(String docId) {
        return documentRepository.findByDocId(docId);
    }
    
    public List<Chunk> getDocumentChunks(Document document) {
        return chunkRepository.findByDocument(document);
    }
}
```

## 3. 代码迁移步骤

### 步骤1: 更新Service层

需要更新使用实体类的Service：

```java
// 旧代码
Document doc = new Document();
doc.setId(UUID.randomUUID().toString());

// 新代码
Document doc = new Document();
doc.setDocId(UUID.randomUUID().toString()); // 业务ID
// id会在保存时自动生成
```

### 步骤2: 更新Chunk关联

```java
// 旧代码
Chunk chunk = Chunk.of(documentId, text, index);

// 新代码
Chunk chunk = Chunk.of(document, text, index); // 直接传Document对象
chunk.setChunkId(UUID.randomUUID().toString()); // 设置业务ID
```

### 步骤3: 处理Embedding存储

Embedding的向量数据会自动序列化/反序列化：

```java
// 使用时无需改变
float[] vector = embedding.getVector(); // 自动从vectorData反序列化
embedding.setVector(newVector); // 自动序列化到vectorData
```

## 4. 数据库表结构

应用启动后，Hibernate会自动创建以下表：

### 主表
- `document` - 文档表
- `chunk` - 文本块表
- `embedding` - 向量嵌入表

### 关联表
- `document_metadata` - 文档元数据
- `chunk_metadata` - 块元数据

### 索引
- `idx_status` - document表的status索引
- `idx_created_at` - document表的创建时间索引
- `idx_document_id` - chunk表的document_id索引
- `idx_object_id` - embedding表的object_id索引

## 5. 配置检查清单

- [ ] 已安装MySQL 8.0+
- [ ] 已创建wikiup数据库
- [ ] 已更新application.properties中的数据库连接信息
- [ ] 已添加MySQL驱动和JPA依赖到pom.xml
- [ ] 已更新Service层代码以适配新的实体类
- [ ] 已测试基本的CRUD操作

## 6. 性能优化建议

### 6.1 向量数据存储优化

当前实现使用字符串序列化存储向量，对于大量向量数据可能不够高效。建议：

1. **短期方案**：使用当前的TEXT字段存储
2. **长期方案**：考虑使用专门的向量数据库（如Milvus、Weaviate）或MySQL的向量插件

### 6.2 查询优化

```java
// 使用懒加载避免加载不必要的数据
@ManyToOne(fetch = FetchType.LAZY)
private Document document;

// 使用JOIN FETCH避免N+1查询
@Query("SELECT d FROM Document d LEFT JOIN FETCH d.chunks WHERE d.id = :id")
Optional<Document> findByIdWithChunks(@Param("id") Long id);
```

### 6.3 批量操作

```java
// 批量保存
List<Chunk> chunks = ...;
chunkRepository.saveAll(chunks);

// 批量删除
chunkRepository.deleteAllInBatch(chunks);
```

## 7. 回滚方案

如果需要回滚到文件存储：

1. 保留旧的Repository接口和实现
2. 使用配置开关控制使用哪种存储方式：

```properties
# application.properties
storage.type=mysql  # 或 file
```

```java
@Configuration
public class StorageConfig {
    
    @Value("${storage.type:mysql}")
    private String storageType;
    
    @Bean
    @ConditionalOnProperty(name = "storage.type", havingValue = "mysql")
    public DocumentRepository mysqlDocumentRepository() {
        // 返回JPA实现
    }
    
    @Bean
    @ConditionalOnProperty(name = "storage.type", havingValue = "file")
    public DocumentRepository fileDocumentRepository() {
        // 返回文件实现
    }
}
```

## 8. 常见问题

### Q1: 如何处理现有的文件数据？

A: 可以编写一个数据迁移脚本，读取文件数据并保存到MySQL：

```java
@Component
public class DataMigration {
    
    @Autowired
    private JpaDocumentRepository documentRepository;
    
    public void migrateFromFiles() {
        // 读取文件数据
        // 转换为实体对象
        // 保存到数据库
    }
}
```

### Q2: 向量搜索性能如何？

A: 当前实现将向量存储为字符串，不支持高效的向量搜索。建议：
- 继续使用现有的向量数据库进行搜索
- MySQL仅用于存储元数据和内容
- 或者升级到支持向量索引的数据库

### Q3: 如何处理大文本内容？

A: 使用TEXT或LONGTEXT字段类型：

```java
@Column(columnDefinition = "LONGTEXT")
private String content;
```

## 9. 测试建议

1. **单元测试**：测试Repository接口的各个方法
2. **集成测试**：测试Service层与数据库的交互
3. **性能测试**：测试大数据量下的查询性能
4. **数据一致性测试**：验证向量序列化/反序列化的正确性

## 10. 下一步

1. 实现数据迁移工具
2. 更新现有Service层代码
3. 添加事务管理
4. 实现缓存机制
5. 考虑分库分表方案（如果数据量很大）

