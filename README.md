# WikiUp - 饥荒百科智能问答系统

<div align="center">

**基于 RAG 架构的游戏知识库问答平台**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Vue.js](https://img.shields.io/badge/Vue.js-3.5-4FC08D.svg)](https://vuejs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.8-blue.svg)](https://www.typescriptlang.org/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

</div>

## 📖 项目简介

WikiUp 是一个专为《饥荒》(Don't Starve) 游戏打造的智能知识库问答系统。通过 RAG（检索增强生成）技术，结合大语言模型和向量数据库，为玩家提供准确、上下文相关的游戏信息查询服务。

### ✨ 核心特性

- 🤖 **智能问答**：基于 DeepSeek LLM 和私有知识库的自然语言问答
- ⚡ **实时流式响应**：WebSocket 实现 ChatGPT 式的流式输出体验
- 📚 **知识库管理**：异步加载和管理 Markdown 格式的游戏知识
- 🔍 **语义检索**：使用 sentence-transformers 多语言模型进行高质量语义搜索
- 🔐 **用户认证**：基于 JWT 的身份认证和角色权限控制（RBAC）
- 👥 **管理后台**：管理员可管理用户和系统配置

## 🏗️ 技术架构

### 前端技术栈

- **框架**：Vue 3 (Composition API + `<script setup>`)
- **状态管理**：Pinia
- **路由**：Vue Router 4
- **HTTP 客户端**：Axios
- **样式**：Tailwind CSS 3
- **UI 组件**：Headless UI for Vue
- **表单验证**：VeeValidate
- **Markdown 渲染**：Marked
- **构建工具**：Vite
- **语言**：TypeScript

### 后端技术栈

- **框架**：Spring Boot 2.7.18
- **语言**：Java 8
- **安全**：Spring Security + JWT (jjwt 0.11.5)
- **数据库**：MySQL 8.0 + Spring Data JPA
- **连接池**：HikariCP
- **WebSocket**：Spring WebSocket
- **响应式**：Spring WebFlux
- **机器学习**：Deep Java Library (DJL) 0.27.0 + PyTorch
- **向量数据库**：ChromaDB (Java client 0.1.7)
- **构建工具**：Maven
- **工具库**：Lombok, Guava

### 系统架构

```
┌─────────────┐      HTTP/WS      ┌─────────────┐
│   Vue 3     │ ◄──────────────► │ Spring Boot │
│  Frontend   │                   │   Backend   │
└─────────────┘                   └──────┬──────┘
                                         │
                    ┌────────────────────┼────────────────────┐
                    │                    │                    │
              ┌─────▼─────┐       ┌─────▼─────┐       ┌─────▼─────┐
              │   MySQL   │       │ ChromaDB  │       │ DeepSeek  │
              │  Database │       │  Vector   │       │    LLM    │
              └───────────┘       └───────────┘       └───────────┘
```

## 🚀 快速开始

### 环境要求

- **Java**: JDK 8+
- **Node.js**: 16+
- **MySQL**: 8.0+
- **Maven**: 3.6+
- **ChromaDB**: 最新版本

### 后端配置

1. 克隆项目
```bash
git clone <repository-url>
cd wikiup
```

2. 配置数据库
```bash
# 创建数据库
mysql -u root -p
CREATE DATABASE wikiup CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. 配置环境变量

在 `backend/.env` 或 `backend/src/main/resources/application.properties` 中配置：
```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/wikiup?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT 配置
jwt.secret=your-secret-key-here
jwt.expiration=86400000

# DeepSeek API
deepseek.api.key=your-deepseek-api-key
deepseek.api.url=https://api.deepseek.com

# ChromaDB 配置
chroma.host=localhost
chroma.port=8000
```

4. 启动后端服务
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

后端服务将在 `http://localhost:8080` 启动

### 前端配置

1. 安装依赖
```bash
cd frontend
npm install
```

2. 配置环境变量

创建 `frontend/.env` 文件：
```env
VITE_API_BASE_URL=http://localhost:8080
```

3. 启动开发服务器
```bash
npm run dev
```

前端应用将在 `http://localhost:5173` 启动

### 初始化数据

1. 运行数据库初始化脚本
```bash
mysql -u root -p wikiup < backend/init.sql
```

2. 准备知识库数据
知识库数据文件不包含在 Git 仓库中。请将饥荒游戏的 Markdown 知识库文件放置在：
```
backend/data/dst/
```
目录中。系统启动时会自动加载这些文件到向量数据库。

3. 默认管理员账号
- 用户名：`admin`
- 密码：`admin123`

## 📁 项目结构

```
wikiup/
├── backend/                 # 后端 Spring Boot 项目
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/ragservice/
│   │       │       ├── config/          # 配置类
│   │       │       ├── controller/      # REST 控制器
│   │       │       ├── dto/            # 数据传输对象
│   │       │       ├── entity/         # JPA 实体
│   │       │       ├── exception/      # 自定义异常
│   │       │       ├── filter/         # 安全过滤器
│   │       │       ├── repository/     # JPA 仓库
│   │       │       ├── security/       # 安全配置
│   │       │       ├── service/        # 业务逻辑
│   │       │       ├── util/           # 工具类
│   │       │       └── websocket/      # WebSocket 处理器
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   └── init.sql
│
├── frontend/                # 前端 Vue 3 项目
│   ├── src/
│   │   ├── assets/         # 静态资源
│   │   ├── components/     # Vue 组件
│   │   ├── composables/    # 组合式函数
│   │   ├── router/         # 路由配置
│   │   ├── services/       # API 服务
│   │   ├── stores/         # Pinia 状态管理
│   │   ├── types/          # TypeScript 类型定义
│   │   ├── views/          # 页面组件
│   │   ├── App.vue
│   │   └── main.ts
│   ├── package.json
│   └── vite.config.ts
│
├── .kiro/                  # Kiro AI 配置
│   └── steering/           # 开发规范和指南
│       ├── product.md
│       ├── tech-stack.md
│       ├── api-conventions.md
│       ├── code-quality.md
│       ├── backend-patterns.md
│       ├── frontend-patterns.md
│       └── development-workflow.md
│
└── README.md
```

## 🔌 API 文档

### 认证接口

#### 用户注册
```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "username": "newuser",
  "password": "SecurePass123",
  "email": "user@example.com"
}
```

#### 用户登录
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "user123",
  "password": "SecurePass123"
}
```

#### 获取当前用户信息
```http
GET /api/v1/auth/me
Authorization: Bearer <token>
```

### 聊天接口

#### 发送问题（REST）
```http
POST /api/v1/chat/query
Authorization: Bearer <token>
Content-Type: application/json

{
  "question": "如何在饥荒中度过冬天？",
  "sessionId": "optional-session-id"
}
```

#### WebSocket 实时聊天
```javascript
// 连接 WebSocket
const ws = new WebSocket('ws://localhost:8080/ws/chat?token=<jwt-token>')

// 发送消息
ws.send(JSON.stringify({
  type: 'question',
  content: '如何在饥荒中度过冬天？',
  sessionId: 'session-123'
}))

// 接收流式响应
ws.onmessage = (event) => {
  const data = JSON.parse(event.data)
  // 处理响应
}
```

### 管理接口

#### 获取所有用户（需要 ADMIN 角色）
```http
GET /api/v1/admin/users
Authorization: Bearer <admin-token>
```

#### 更新用户角色（需要 ADMIN 角色）
```http
PUT /api/v1/admin/users/{id}/roles
Authorization: Bearer <admin-token>
Content-Type: application/json

{
  "roles": ["USER", "ADMIN"]
}
```

完整 API 文档请参考：[API Conventions](.kiro/steering/api-conventions.md)

## 🛠️ 开发指南

### 代码规范

项目遵循严格的代码质量标准，详见：
- [代码质量标准](.kiro/steering/code-quality.md)
- [后端开发模式](.kiro/steering/backend-patterns.md)
- [前端开发模式](.kiro/steering/frontend-patterns.md)

### Git 提交规范

使用 Conventional Commits 格式：
```
<type>(<scope>): <subject>

<body>

<footer>
```

类型：
- `feat`: 新功能
- `fix`: Bug 修复
- `docs`: 文档更新
- `style`: 代码格式调整
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建/工具链更新

示例：
```
feat(auth): 添加 JWT token 刷新功能

实现 token 过期时自动刷新机制
新增 /api/v1/auth/refresh 端点

Closes #123
```

## 🧪 测试

### 后端测试
```bash
cd backend
mvn test
```

### 前端测试
```bash
cd frontend
npm run test
```

## 📦 构建部署

### 后端打包
```bash
cd backend
mvn clean package
java -jar target/rag-service-0.0.1-SNAPSHOT.jar
```

### 前端打包
```bash
cd frontend
npm run build
# 构建产物在 dist/ 目录
```

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'feat: add some amazing feature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

## 👥 作者

- 项目维护者：[Your Name]

## 🙏 致谢

- [Don't Starve Wiki](https://dontstarve.fandom.com/) - 游戏知识来源
- [DeepSeek](https://www.deepseek.com/) - LLM 服务提供
- [ChromaDB](https://www.trychroma.com/) - 向量数据库
- [DJL](https://djl.ai/) - 深度学习库

## 📞 联系方式

如有问题或建议，请提交 Issue 或联系项目维护者。

---

<div align="center">
Made with ❤️ for Don't Starve players
</div>
