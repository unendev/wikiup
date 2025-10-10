# MySQL 数据库配置指南

## 1. 安装MySQL

### Windows:
1. 下载MySQL 8.0+ 安装包: https://dev.mysql.com/downloads/mysql/
2. 运行安装程序，选择"Developer Default"
3. 设置root密码（默认配置中为：root）
4. 完成安装

### 验证安装:
```bash
mysql --version
```

## 2. 创建数据库

### 方法一：使用MySQL命令行
```bash
mysql -u root -p
```

然后执行：
```sql
CREATE DATABASE wikiup DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 方法二：使用提供的SQL脚本
```bash
mysql -u root -p < init.sql
```

## 3. 配置说明

### 数据库连接配置（application.properties）
```properties
# 数据库URL
spring.datasource.url=jdbc:mysql://localhost:3306/wikiup?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true

# 用户名和密码（请根据实际情况修改）
spring.datasource.username=root
spring.datasource.password=root
```

### 重要配置项说明：

#### HikariCP连接池配置
- `maximum-pool-size`: 最大连接数（10）
- `minimum-idle`: 最小空闲连接数（5）
- `connection-timeout`: 连接超时时间（20秒）

#### JPA/Hibernate配置
- `spring.jpa.hibernate.ddl-auto=update`: 自动更新表结构（生产环境建议改为validate）
- `spring.jpa.show-sql=true`: 显示SQL语句（开发环境有用，生产环境建议关闭）
- `spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect`: MySQL 8方言

## 4. 修改数据库连接信息

如果你的MySQL配置不同，请修改 `application.properties` 文件中的以下配置：

```properties
spring.datasource.url=jdbc:mysql://你的主机:端口/数据库名?参数
spring.datasource.username=你的用户名
spring.datasource.password=你的密码
```

## 5. 环境变量配置（推荐）

为了安全性，建议使用环境变量存储敏感信息：

### Windows PowerShell:
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/wikiup?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_password"
```

然后修改 `application.properties`:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## 6. 数据库表结构

应用启动后，JPA会自动创建以下表（基于Entity类）：
- `document`: 文档表
- `chunk`: 文本块表
- `embedding`: 向量嵌入表
- 其他业务表...

## 7. 常见问题

### 问题1: 连接超时
确保MySQL服务正在运行：
```bash
# 检查MySQL服务状态
net start | findstr MySQL
```

### 问题2: 字符编码问题
确保数据库和表都使用utf8mb4字符集

### 问题3: 时区问题
URL中已包含 `serverTimezone=Asia/Shanghai` 参数

### 问题4: Public Key Retrieval is not allowed
URL中已包含 `allowPublicKeyRetrieval=true` 参数

## 8. 启动应用

配置完成后，启动Spring Boot应用：
```bash
cd backend
mvnw spring-boot:run
```

应用启动时会自动：
1. 连接到MySQL数据库
2. 创建/更新表结构
3. 初始化数据（如果配置了DataInitializer）

## 9. 验证数据库连接

启动应用后，检查日志中是否有：
- "HikariPool-1 - Start completed" - 连接池启动成功
- "Hibernate: create table..." - 表创建语句
- 没有连接错误信息

## 10. 生产环境建议

1. 修改 `spring.jpa.hibernate.ddl-auto=validate` （不自动修改表结构）
2. 关闭 `spring.jpa.show-sql=false` （不打印SQL）
3. 使用环境变量存储数据库密码
4. 配置合适的连接池大小
5. 启用SSL连接（修改useSSL=true并配置证书）

