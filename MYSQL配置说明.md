# MySQL数据库配置说明

## ✅ 配置已完成

本项目已成功配置MySQL数据库支持！

## 📁 相关文件

所有MySQL相关的配置和文档都在 `backend` 目录下：

### 配置文件
- ✅ `backend/pom.xml` - 已添加MySQL驱动和JPA依赖
- ✅ `backend/src/main/resources/application.properties` - 已配置数据库连接

### 实体类（已添加JPA注解）
- ✅ `backend/src/main/java/com/example/ragservice/model/Document.java`
- ✅ `backend/src/main/java/com/example/ragservice/model/Chunk.java`
- ✅ `backend/src/main/java/com/example/ragservice/model/Embedding.java`

### Repository接口
- ✅ `backend/src/main/java/com/example/ragservice/repository/JpaDocumentRepository.java`
- ✅ `backend/src/main/java/com/example/ragservice/repository/JpaChunkRepository.java`
- ✅ `backend/src/main/java/com/example/ragservice/repository/JpaEmbeddingRepository.java`

### 服务实现示例
- ✅ `backend/src/main/java/com/example/ragservice/service/impl/JpaDocumentServiceImpl.java`

### 数据库脚本
- ✅ `backend/init.sql` - 数据库初始化脚本

### 文档
- 📖 `backend/MYSQL_QUICKSTART.md` - **快速入门指南（推荐先看这个）**
- 📖 `backend/MYSQL_SETUP.md` - MySQL详细配置说明
- 📖 `backend/MIGRATION_GUIDE.md` - 数据迁移指南
- 📖 `backend/README_MYSQL.md` - 完整说明文档

## 🚀 快速开始（3步）

### 1. 创建MySQL数据库

```bash
mysql -u root -p
```

```sql
CREATE DATABASE wikiup DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

或使用提供的脚本：
```bash
mysql -u root -p < backend/init.sql
```

### 2. 修改数据库密码

编辑 `backend/src/main/resources/application.properties`：

```properties
spring.datasource.password=你的MySQL密码
```

### 3. 启动应用

```bash
cd backend
mvnw spring-boot:run
```

## 📊 自动创建的数据库表

应用启动时会自动创建：
- `document` - 文档表
- `chunk` - 文本块表  
- `embedding` - 向量嵌入表
- `document_metadata` - 文档元数据表
- `chunk_metadata` - 块元数据表

## 📖 详细文档

请查看 `backend/MYSQL_QUICKSTART.md` 获取：
- 详细的配置步骤
- 使用示例代码
- 故障排除指南
- 性能优化建议

## ⚙️ 默认配置

```properties
# 数据库连接（可根据实际情况修改）
spring.datasource.url=jdbc:mysql://localhost:3306/wikiup?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root

# 连接池配置
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5

# JPA配置
spring.jpa.hibernate.ddl-auto=update  # 自动更新表结构
spring.jpa.show-sql=true  # 显示SQL语句
```

## ❓ 常见问题

### Q: 需要手动创建表吗？
A: 不需要。应用启动时会自动创建所有表。

### Q: 如何验证配置成功？
A: 启动应用后，在日志中看到 "HikariPool-1 - Start completed" 即表示成功。

### Q: 是否影响现有功能？
A: 不影响。新的JPA功能是额外添加的，可以与现有代码并存。

## 🎯 下一步

1. ✅ MySQL已配置完成
2. ⏭️ 阅读快速入门文档：`backend/MYSQL_QUICKSTART.md`
3. ⏭️ 根据需求调整配置
4. ⏭️ 开始使用JPA进行数据库操作

---

**配置完成时间**: 2025-10-10  
**状态**: ✅ 就绪

