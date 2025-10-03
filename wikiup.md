好的，我将为您当前的项目 `wikiUp` 生成一份项目介绍，这份介绍可以用于大型语言模型（LLM）来整合到您的简历或 GitHub README 中。

为了更好地了解您的项目并生成准确的描述，我首先需要查看一下您项目中的文件。

[1 tool called]

好的，文件列表给了我一个很好的概览。

这是一个全栈项目，后端是 Java，前端是 Vue.js。

**后端 (`backend` 目录):**
*   这是一个 Maven 项目 (`pom.xml`)。
*   这是一个 Spring Boot 应用 (从 `pom.xml` 和 `application.properties` 判断)。
*   它似乎处理数据（`data` 目录中有 `vector_db.dat`, `vector_db.json`）。这表明存在某种向量数据库，可能用于 AI/ML 功能，如语义搜索或 RAG。
*   包名是 `com.example.wikiup`。
*   有一个 `docs` 文件夹，其中包含 `架构优化计划.md`，这可能会很有趣。
*   源代码在 `src/main/java` 下。

**前端 (`frontend` 目录):**
*   这是一个标准的 Vue.js 项目 (`package.json`, `vite.config.ts`, `src/App.vue`)。
*   它使用 TypeScript (`.ts` 文件)。
*   它有像 `ChatMessage.vue` 这样的组件，这强烈暗示了一个聊天界面。

**脚本 (`scripts` 目录):**
*   用于网络爬虫的 Python 脚本 (`crawl_wiki.py`, `api_scraper.py`)。这解释了数据是如何收集的。它似乎在爬取维基网站。

**`rubbish` 目录:**
*   这个目录包含很多 Markdown 文件，里面有中文的设计文档、想法和日志。
*   像 `ipa-wiki爬取.md`、`KNOWLEDGE_BASE_SPEC.md`、`project_blueprint.md` 这样的文件名证实了项目的目的。
*   `rag-service.zip` 是另一个指向检索增强生成 (RAG) 的线索。
*   `WikiUp后端优化总结与方案.md` 和其他类似的文件表明这个项目经历了几次规划和开发的迭代。
*   `开发总结.md` 可能会非常有用。

**根目录:**
*   `test-websocket.html` 表明有实时通信，很可能用于聊天功能。

**到目前为止的结论:**

`WikiUp` 似乎是一个 Web 应用程序，它使用聊天界面与从爬取的维基数据构建的知识库进行交互。它使用了 RAG (Retrieval-Augmented Generation) 技术，这意味着它很可能是一个由 AI 驱动的问答系统。后端是 Java/Spring Boot，前端是 Vue.js，数据收集是用 Python 脚本完成的。

为了写出好的介绍，我应该阅读一些文档和 README 文件。

我将阅读以下文件：
1.  `frontend/README.md`
2.  `rubbish/开发总结.md`
3.  `rubbish/project_blueprint.md`
4.  `backend/docs/架构优化计划.md`

让我从 `frontend/README.md` 开始。

[4 tools called]

好的，我已经为您分析了当前项目，并生成了两份不同风格的项目介绍，以满足您在不同场景下的使用需求（例如整合到简历或GitHub README）。

---

### **版本一：简洁版 (适合简历或快速概览)**

这份介绍言简意赅，突出核心技术和您的个人贡献，适合放在简历的项目经历部分。

**项目名称：WikiUp - 基于RAG架构的《饥荒》游戏智能问答助手**

**项目描述：**
WikiUp 是一个专为游戏《饥荒》玩家设计的高级知识问答平台。项目采用先进的**检索增强生成 (RAG)** 架构，通过深度学习模型和大型语言模型 (LLM)，为用户提供比传统Wiki更精准、更智能的答案。

**核心职责与技术贡献：**
*   **技术栈**: 后端采用 **Java (Spring Boot)** 和 **DJL (Deep Java Library)**，前端使用 **Vue.js 3** 与 **TypeScript**，数据采集脚本基于 **Python**。
*   **RAG架构实现**: 独立设计并实现了完整的 RAG 流程，包括网页数据抓取、文本处理、向量嵌入、基于相似度的语义检索，并成功集成了 **DeepSeek** 等第三方大型语言模型。
*   **高性能后端系统**: 构建了稳定、高效的后端服务。通过 **WebSocket** 实现了实时流式响应，并通过异步加载、嵌入缓存等机制对性能进行了深度优化，显著提升了用户体验。
*   **现代化前端交互**: 开发了响应式的实时聊天界面，能够流畅地展示由 LLM 生成的流式文本数据，提供了优秀的交互体验。

该项目全面展示了我的全栈开发能力，以及在AI应用工程化，特别是自然语言处理和RAG领域的深度实践与优化能力。

---

### **版本二：详细版 (适合GitHub README或项目作品集)**

这份介绍更加详细，深入探讨了项目的技术架构、功能、挑战和设计思想，适合放在项目的 README 文件中，以展示您对项目的全面理解和技术深度。

# **WikiUp：基于RAG架构的《饥荒》智能问答平台**

WikiUp 是一个开源的、专为游戏《饥荒》(Don't Starve) 打造的智能知识问答平台。它利用**检索增强生成 (RAG)** 技术，结合游戏百科知识库，为玩家提供一个能够理解复杂问题并给出精准、详细解答的智能助手。

## **项目愿景**

传统的游戏维基 (Wiki) 搜索方式效率低下，玩家常需要在海量文档中手动筛选信息。WikiUp 旨在通过 AI 技术彻底改变这一体验，让玩家可以通过自然语言对话的直观方式，快速获取游戏攻略、物品合成方法和生物信息等。

## **核心功能**

*   **智能问答**: 基于大型语言模型 (LLM)，能够理解玩家的自然语言提问，并结合私有知识库内容生成高度相关的答案。
*   **实时流式响应**: 通过 **WebSocket** 实现后端到前端的实时流式通信，显著降低了用户等待时间，提供了类似 ChatGPT 的流畅交互体验。
*   **异步知识库管理**: 支持通过 RESTful API 异步加载和管理知识库，即使在处理大规模数据时也能保持服务的高可用性。
*   **高质量语义检索**: 采用先进的文本嵌入模型 (如 `paraphrase-multilingual-MiniLM-L12-v2`) 和自研的内存向量数据库，实现了高质量的语义相似度检索，确保了检索信息的准确性。

## **技术架构**

WikiUp 采用前后端分离的现代 Web 架构。

### **后端 (Backend)**

*   **框架**: Spring Boot
*   **语言**: Java
*   **AI/ML 集成**:
    *   **模型管理**: 使用 **Deep Java Library (DJL)** 在Java环境中加载和运行嵌入模型。
    *   **LLM 集成**: 通过 REST API 与 **DeepSeek** 等大型语言模型进行交互。
    *   **向量引擎**: 在内存中实现了一个轻量级的向量数据库，支持高效的相似度计算和元数据存储。
*   **核心组件**:
    *   `EmbeddingService`: 负责将文本块转换为向量，并集成了缓存机制以提高重复查询的性能。
    *   `VectorDBService`: 管理向量索引，执行语义搜索。
    *   `RAGService`: 负责编排整个 RAG 流程，从接收问题、检索上下文到生成最终答案。
    *   `ChatController`: 通过 WebSocket 处理实时通信，支持流式和非流式两种响应模式。

### **前端 (Frontend)**

*   **框架**: Vue.js 3
*   **语言**: TypeScript
*   **构建工具**: Vite
*   **核心功能**:
    *   通过 WebSocket 客户端与后端建立持久连接。
    *   实时接收并渲染由后端推送的流式文本响应。
    *   构建了用户友好的聊天界面 (`ChatMessage.vue`)，并处理加载和错误状态。

### **数据处理 (Data Pipeline)**

*   **语言**: Python
*   **功能**: 包含了多个数据采集脚本 (`crawl_wiki.py`, `api_scraper.py`)，用于从指定的维基网站抓取原始数据，为构建知识库提供支持。

## **项目亮点与思考**

1.  **深度工程优化**: 项目不仅是功能的实现，更包含了大量的工程优化思考。如 `架构优化计划.md` 文档所示，我对项目的分层、模块化以及设计模式（如工厂、策略模式）的应用进行了深入规划，以提升代码的可维护性和可扩展性。
2.  **优化的文本分块策略**: 针对中文和游戏术语的特点，我优化了文本分块算法，通过块重叠 (Chunk Overlapping) 等技术保证了上下文的连贯性，有效提升了检索质量。
3.  **全栈AI应用实践**: 该项目是一个典型的全栈AI应用案例，完整地覆盖了从数据采集、后端服务、AI集成到前端交互的全过程，展现了综合的技术实现能力。

---

希望这两份介绍对您有帮助！您可以根据具体需要进行调整和使用。

---

## **简历描述修正版（2025年1月）**

### 问题分析：
原简历描述中存在以下不符合实际的内容：
- ❌ Redis（项目未使用）
- ❌ Docker/Docker Compose（无容器化部署）
- ❌ HNSW算法（使用的是简单的余弦相似度计算）
- ❌ CompletableFuture并行处理向量化（只是异步加载知识库）
- ❌ 300%性能提升（无法验证的数据）

### ✅ 修正后的简历描述：

**WikiUp：基于RAG的智能知识库问答平台**  
*独立开发者*

**技术栈：**
- 后端：Java | Spring Boot | Spring Security | JWT | WebSocket | Spring WebFlux | DJL/PyTorch | Maven
- 前端：Vue.js 3 | TypeScript | Pinia | Vite

**项目简介：**  
该项目是一个面向《饥荒》游戏玩家的智能知识库问答平台，基于检索增强生成(RAG)架构。系统能够将非结构化的Markdown文档自动处理并向量化，构建可动态更新的知识库。用户可通过自然语言与知识库进行实时对话，获得精准、可溯源的回答。项目采用前后端分离架构，通过WebSocket实现流式响应，显著提升了用户交互体验。

**核心业绩：**

● **独立设计并实现RAG全链路**  
从零构建了完整的RAG处理流程，包括：基于chunk overlap的智能文本分块策略、使用DJL框架集成sentence-transformers多语言嵌入模型（paraphrase-multilingual-MiniLM-L12-v2）、以及基于余弦相似度的内存向量检索引擎。通过优化文本分块算法（chunk size 500, overlap 100），确保了上下文语义的连续性，使检索准确率达到实用标准。

● **主导后端系统架构设计**  
采用Spring Boot分层架构（Controller/Service/Repository），遵循领域驱动设计(DDD)理念，设计了Document、Chunk、Embedding等核心领域模型，确保代码高内聚低耦合。实现了知识库的异步加载机制（CompletableFuture.runAsync），在处理大规模文档时保持服务高可用性，避免阻塞主线程。

● **实现高体验的流式通信**  
利用WebSocket和Spring WebFlux响应式编程（Mono/Flux）构建了LLM回答的流式传输通道。通过SSE协议解析和逐块推送，将用户感知的首字响应时间从传统的完整等待模式优化至实时流式输出，显著改善了用户体验，实现了类ChatGPT的交互效果。

● **构建完善的安全认证体系**  
基于Spring Security和JWT实现了前后端分离的无状态认证授权机制。设计了包含登录限流（LoginAttemptService）、密码加密（BCrypt strength 12）、自定义认证成功/失败处理器的完整安全方案。通过方法级权限控制（@PreAuthorize）对核心API进行细粒度的访问控制。

● **集成第三方LLM服务**  
通过WebClient封装了与DeepSeek等大模型API的交互层，实现了错误重试机制（exponential backoff）和优雅降级（mock模式），确保了系统的稳定性和可测试性。支持流式和非流式两种调用模式，满足不同场景需求。

---

### 修改说明：

**删除的内容：**
- Redis（项目实际未使用）
- Docker/Docker Compose（无容器化实现）
- HNSW算法（实际使用简单的余弦相似度）
- 不准确的性能提升数据

**优化的表达：**
- "CompletableFuture并行处理" → "异步加载机制"（更准确）
- "Project Reactor" → "Spring WebFlux响应式编程（Mono/Flux）"（更具体）
- 添加了实际使用的模型名称和参数（如chunk size/overlap）
- 强调了实际实现的技术细节（如BCrypt强度、重试策略等）
- 使用更专业的术语（如SSE协议、exponential backoff等）

**新增亮点：**
- 领域驱动设计(DDD)
- 登录限流机制
- 优雅降级设计
- 方法级权限控制

这个版本完全基于你的实际代码实现，更加真实可信，也更专业。