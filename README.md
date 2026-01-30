# RAG 智能问答服务

一个全栈的检索增强生成（RAG）服务，具备实时聊天、用户认证和向量相似度搜索功能。

## 功能特性

- 🔐 **用户认证** - 基于 JWT 的身份验证，集成 Spring Security
- 💬 **实时聊天** - WebSocket 驱动的聊天界面
- 🤖 **RAG 系统** - 使用 ChromaDB 和 DJL 的智能问答
- 👥 **用户管理** - 系统管理员面板
- 🔍 **向量搜索** - 基于 ChromaDB 的语义搜索
- 📱 **响应式界面** - 使用 Vue 3 和 Tailwind CSS 构建的现代化界面

## 技术栈

### 后端
- Spring Boot 2.7.18
- Java 8
- MySQL + JPA/Hibernate
- JWT 身份验证
- ChromaDB（向量数据库）
- Deep Java Library (DJL) + PyTorch
- WebSocket (STOMP)

### 前端
- Vue 3（组合式 API）
- TypeScript
- Vite
- Tailwind CSS
- Pinia（状态管理）
- Vue Router
- Axios

## 环境要求

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6+
- **Node.js**: 18+
- **MySQL**: 8.0+
- **ChromaDB**: 运行中的实例

## 快速开始

### 1. 数据库设置

```bash
# 创建数据库并运行初始化脚本
mysql -u root -p < backend/init.sql
```

### 2. 后端设置

```bash
cd backend

# 配置环境变量
# 复制 .env.example 为 .env 并更新配置
# 必需配置：数据库凭据、JWT 密钥、ChromaDB URL

# 运行应用
mvnw spring-boot:run
```

后端将在 `http://localhost:8080` 启动

### 3. 前端设置

```bash
cd frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

前端将在 `http://localhost:5173` 启动

## 环境配置

### 后端 (.env)
```properties
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/ragdb
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=your-secret-key-min-256-bits
JWT_EXPIRATION=86400000
CHROMA_DB_URL=http://localhost:8000
```

### 前端 (.env)
```properties
VITE_API_BASE_URL=http://localhost:8080
```

## 项目结构

```
├── backend/                 # Spring Boot 应用
│   ├── src/main/java/      # Java 源代码
│   │   └── com/example/ragservice/
│   │       ├── config/     # 配置类
│   │       ├── controller/ # REST 控制器
│   │       ├── service/    # 业务逻辑
│   │       ├── repository/ # 数据访问层
│   │       ├── model/      # 实体类
│   │       ├── dto/        # 数据传输对象
│   │       └── security/   # 安全配置
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/               # Vue 3 应用
    ├── src/
    │   ├── components/     # 可复用组件
    │   ├── views/          # 页面组件
    │   ├── router/         # 路由配置
    │   ├── stores/         # Pinia 状态管理
    │   ├── services/       # API 服务
    │   ├── composables/    # 组合式工具函数
    │   └── types/          # TypeScript 类型定义
    └── package.json
```

## 开发指南

### 后端开发
```bash
cd backend
mvnw spring-boot:run
```

### 前端开发
```bash
cd frontend
npm run dev
```

### 生产环境构建

**后端：**
```bash
cd backend
mvnw clean package -DskipTests
# 输出：target/rag-service-0.0.1-SNAPSHOT.jar
```

**前端：**
```bash
cd frontend
npm run build
# 输出：dist/
```

## API 接口

### 认证相关
- `POST /api/auth/register` - 用户注册
- `POST /api/auth/login` - 用户登录
- `POST /api/auth/refresh` - 刷新 JWT 令牌

### 聊天相关
- `POST /api/chat/message` - 发送聊天消息
- `GET /api/chat/history` - 获取聊天历史
- `WS /ws` - WebSocket 连接

### 管理员相关
- `GET /api/admin/users` - 列出所有用户（仅管理员）
- `DELETE /api/admin/users/{id}` - 删除用户（仅管理员）

## 测试

### 后端测试
```bash
cd backend
mvnw test
```

### 前端测试
```bash
cd frontend
npm run test
```

## 部署

详细的部署说明请参考 [deployment.md](.kiro/steering/deployment.md)，包括：
- 生产环境构建流程
- Nginx 配置
- Systemd 服务设置
- 安全检查清单

## 开发规范

- **后端规范**：参考 [backend-standards.md](.kiro/steering/backend-standards.md)
- **前端规范**：参考 [frontend-standards.md](.kiro/steering/frontend-standards.md)
- **Git 工作流**：参考 [git-workflow.md](.kiro/steering/git-workflow.md)
- **测试规范**：参考 [testing-standards.md](.kiro/steering/testing-standards.md)

## 贡献指南

1. 从 `develop` 分支创建功能分支
2. 遵循 `.kiro/steering/` 中的编码规范
3. 为新功能编写测试
4. 提交带有清晰描述的 Pull Request
5. 确保所有测试通过且代码构建成功

## 许可证

[在此添加许可证信息]

## 支持

如有问题或疑问，请在仓库中提交 issue。
