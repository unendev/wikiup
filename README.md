# WikiUp - 基于RAG的企业级智能知识库平台

WikiUp是一个基于检索增强生成（RAG）架构，面向企业内部知识库的智能问答平台。平台通过深度学习模型对非结构化的Markdown文档（如产品手册、技术Wiki）进行处理，构建向量化知识索引，并允许用户通过自然语言对话的方式进行高效、精准的信息检索。项目采用前后端分离的全栈架构，后端基于Spring Boot，前端基于Vue 3，通过WebSocket实现AI回答的实时流式传输，旨在提供企业级的智能信息服务。

## ✨ 核心功能

*   **智能问答**：通过自然语言对话，精准地从知识库中获取信息。
*   **源文追溯**：每个回答都会附带相关源文档链接，方便用户核实信息来源。
*   **流式响应**：AI回答以打字机效果实时流式传输，提升用户交互体验。
*   **安全认证**：基于JWT的用户认证和RBAC权限控制，保障知识库安全。
*   **知识库管理**：提供后台管理界面，方便管理员更新和维护知识库。

## 🚀 技术栈

*   **后端：** Spring Boot, Spring Security, WebSocket, DJL, PyTorch, CompletableFuture, Redis, Docker
*   **前端：** Vue 3, TypeScript, Pinia, Vue Router, Axios, WebSocket, Tailwind CSS
*   **AI模型：** `paraphrase-multilingual-MiniLM-L12-v2` (文本嵌入), DeepSeek API (文本生成)

## 📦 快速开始

### 环境准备

*   Java 1.8
*   Maven
*   Node.js
*   Docker

### 本地运行

1.  **克隆仓库**
    ```bash
    git clone https://github.com/helpfulcraft/wikiup.git
    cd wikiup
    ```

2.  **启动后端**
    ```bash
    cd backend
    mvn spring-boot:run
    ```

3.  **启动前端**
    ```bash
    cd frontend
    npm install
    npm run dev
    ```

4.  **访问**
    在浏览器中打开 `http://localhost:5173`

## 📝 未来计划

*   [ ] 实现知识库增量更新
*   [ ] 添加缓存策略优化
*   [ ] 实现CI/CD自动化部署
*   [ ] 支持更多知识库格式 (PDF, Word)

## 🤝 贡献

欢迎提交Issue和Pull Request，共同改进项目！ 