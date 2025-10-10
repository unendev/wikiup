# MySQL快速入门指南

## 1. 配置完成清单

✅ 已添加MySQL相关依赖到 `pom.xml`：
- MySQL驱动 (mysql-connector-java 8.0.33)
- Spring Data JPA
- HikariCP连接池

✅ 已配置数据库连接 `application.properties`：
- 数据库URL、用户名、密码
- HikariCP连接池参数
- JPA/Hibernate配置

✅ 已创建JPA实体类：
- Document、Chunk、Embedding（含JPA注解）

✅ 已创建JPA Repository接口：
- JpaDocumentRepository
- JpaChunkRepository  
- JpaEmbeddingRepository

✅ 已创建示例Service实现：
- JpaDocumentServiceImpl

## 2. 快速开始（3步）

### 第1步：创建MySQL数据库

```bash
# 启动MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE wikiup DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

或者使用提供的脚本：
```bash
mysql -u root -p < init.sql
```

### 第2步：配置数据库连接

编辑 `src/main/resources/application.properties`，修改以下配置：

```properties
# 根据你的MySQL配置修改
spring.datasource.url=jdbc:mysql://localhost:3306/wikiup?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=你的密码
```

### 第3步：启动应用

```bash
cd backend
mvnw spring-boot:run
```

应用启动时会自动：
1. 连接MySQL数据库
2. 创建所需的表结构
3. 准备就绪接受请求

## 3. 验证配置

### 查看日志

启动时应该看到类似的日志：

```
Hibernate: create table document (...)
Hibernate: create table chunk (...)
Hibernate: create table embedding (...)
HikariPool-1 - Start completed.
```

### 查看数据库表

```sql
USE wikiup;
SHOW TABLES;

-- 应该看到以下表：
-- document
-- chunk  
-- embedding
-- document_metadata
-- chunk_metadata
```

### 测试API（如果有）

```bash
# 创建文档
curl -X POST http://localhost:8080/api/documents \
  -H "Content-Type: application/json" \
  -d '{"title":"测试文档","content":"这是测试内容"}'

# 查询文档  
curl http://localhost:8080/api/documents
```

## 4. 使用示例

### 在Controller中使用

```java
@RestController
@RequestMapping("/api/mysql/documents")
public class MySQLDocumentController {
    
    @Autowired
    private JpaDocumentServiceImpl documentService;
    
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document saved = documentService.saveDocument(document);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/{docId}")
    public ResponseEntity<Document> getDocument(@PathVariable String docId) {
        return documentService.findByDocId(docId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.findAllDocuments());
    }
}
```

### 在Service中使用

```java
@Service
public class MyRAGService {
    
    @Autowired
    private JpaDocumentServiceImpl documentService;
    
    @Autowired
    private JpaChunkRepository chunkRepository;
    
    public void processDocument(String filePath) {
        // 1. 创建文档
        Document doc = new Document();
        doc.setTitle("文档标题");
        doc.setContent("文档内容...");
        doc.setPath(filePath);
        doc.setStatus(Document.DocumentStatus.CREATED);
        
        // 2. 保存文档
        doc = documentService.saveDocument(doc);
        
        // 3. 创建文档块
        Chunk chunk = new Chunk();
        chunk.setDocument(doc);
        chunk.setText("文档的第一个块...");
        chunk.setIndex(0);
        
        // 4. 保存块
        documentService.saveChunk(chunk);
        
        // 5. 更新状态
        documentService.updateDocumentStatus(doc.getDocId(), 
            Document.DocumentStatus.CHUNKED);
    }
}
```

## 5. 常用操作

### 保存文档和块

```java
// 创建文档
Document doc = new Document();
doc.setTitle("我的文档");
doc.setContent("文档内容");
doc = documentService.saveDocument(doc);

// 创建块
List<Chunk> chunks = new ArrayList<>();
for (int i = 0; i < 5; i++) {
    Chunk chunk = new Chunk();
    chunk.setDocument(doc);
    chunk.setText("块内容 " + i);
    chunk.setIndex(i);
    chunks.add(chunk);
}

// 批量保存
documentService.saveAllChunks(chunks);
```

### 查询文档

```java
// 根据业务ID查询
Optional<Document> doc = documentService.findByDocId("doc-id-123");

// 根据状态查询
List<Document> processingDocs = documentService.findByStatus(
    Document.DocumentStatus.PROCESSING);

// 搜索标题
List<Document> results = documentService.searchByTitle("关键词");
```

### 删除文档

```java
// 删除文档（会自动删除关联的块）
documentService.deleteDocument("doc-id-123");
```

## 6. 配置选项

### 开发环境配置

```properties
# 显示SQL（用于调试）
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# 自动更新表结构
spring.jpa.hibernate.ddl-auto=update
```

### 生产环境配置

```properties
# 不显示SQL
spring.jpa.show-sql=false

# 只验证表结构（不自动修改）
spring.jpa.hibernate.ddl-auto=validate

# 性能优化
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

# 连接池调优
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=10
```

## 7. 故障排除

### 问题1: 无法连接数据库

**错误**: `CommunicationsException: Communications link failure`

**解决**:
1. 检查MySQL服务是否运行
2. 检查端口3306是否正确
3. 检查防火墙设置

### 问题2: 数据库不存在

**错误**: `Unknown database 'wikiup'`

**解决**:
```sql
CREATE DATABASE wikiup DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 问题3: 认证失败

**错误**: `Access denied for user 'root'@'localhost'`

**解决**:
1. 检查用户名和密码
2. 确保用户有权限访问wikiup数据库

```sql
GRANT ALL PRIVILEGES ON wikiup.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 问题4: 表结构不匹配

**错误**: `Table 'wikiup.document' doesn't exist`

**解决**:
1. 确保 `spring.jpa.hibernate.ddl-auto=update`
2. 重启应用让Hibernate创建表
3. 或手动执行建表SQL

## 8. 下一步

- [ ] 阅读 [MIGRATION_GUIDE.md](MIGRATION_GUIDE.md) 了解详细的迁移步骤
- [ ] 阅读 [MYSQL_SETUP.md](MYSQL_SETUP.md) 了解详细的配置说明
- [ ] 根据业务需求调整实体类和Repository
- [ ] 实现数据迁移（从文件到数据库）
- [ ] 添加更多业务逻辑
- [ ] 进行性能测试和优化

## 9. 相关文档

- [MYSQL_SETUP.md](MYSQL_SETUP.md) - MySQL详细配置指南
- [MIGRATION_GUIDE.md](MIGRATION_GUIDE.md) - 数据迁移指南
- [init.sql](init.sql) - 数据库初始化脚本

## 10. 技术栈

- **数据库**: MySQL 8.0+
- **ORM**: Spring Data JPA + Hibernate
- **连接池**: HikariCP
- **字符集**: UTF-8 (utf8mb4)
- **时区**: Asia/Shanghai

