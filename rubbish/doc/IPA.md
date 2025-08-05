# Wiki-上位 V0.2 - 项目规划 (IPA)

本文档旨在记录项目的当前状态、未来规划和下一步行动，以确保开发过程清晰、有序。

---

## I (Information / 情报) - 我们现在在哪里？

项目已成功完成 **最小可行产品 (MVP)** 的开发和调试。应用目前处于一个功能完整、端到端链路通畅的稳定状态。

### V1.0 核心功能清单：
- **[✓] 后端服务:** 基于 FastAPI，稳定可靠。
- **[✓] 知识库系统:** 自动从 `data/` 目录加载 Markdown 文件。
- **[✓] 检索流程:**
    - **[✓] 语义检索:** 使用 SentenceTransformer 模型将文本向量化并存入 ChromaDB。
    - **[✓] 重排优化 (Re-ranking):** 集成 `BAAI/bge-reranker-large` 模型，在生成答案前对检索结果进行二次排序，提高精准度。
- **[✓] 生成流程:** 集成本地大语言模型，根据检索信息生成连贯的答案。
- **[✓] 前端界面:** 基于 Vue.js 3，提供实时聊天和来源展示功能。
- **[✓] 端到端调试:** 已解决所有已知的依赖、环境、路径和数据类型错误。

**结论:** 我们拥有一个坚实的基础，可以此为起点进行后续的功能迭代和性能优化。

---

## P (Plan / 计划) - 我们要去哪里？

为了让应用从"可用"进化到"优秀"，我们规划了以下几个主要迭代方向：

### Task 2: Vector Database and Recall Enhancement

-   [x] ~~**Task 2.1: Integrate Hybrid Search and Re-ranker** - Implement BM25 for keyword search and a re-ranker to merge and re-rank results from both vector and keyword searches.~~ (Implementation complete, pending data)
-   [ ] **Task 2.2: Test and Evaluate** - Systematically test the new hybrid search with the expanded knowledge base and evaluate its performance on query accuracy and relevance.

### Task 3: Knowledge Acquisition & Data Processing

-   [x] ~~**Task 3.1: Develop Wiki content scraping script** - Create a Python script to automatically scrape content from a wiki (e.g., Don't Starve Fandom Wiki).~~ (Completed - MVP semi-automated script)
-   [x] ~~**Task 3.2: Establish Knowledge Base Specification** - Define a clear, structured format for the knowledge base content, likely using Markdown with YAML frontmatter for metadata.~~
-   [x] ~~**Task 3.3: Populate and Process Knowledge Base** - Use the semi-automated script and manual refinement to add more documents to `data/`.~~ (Completed)
-   [x] ~~**Task 3.4: Implement Knowledge Base Loader** - Develop a robust loader in `vector_store.py` that can parse all Markdown files from the `data/` directory, respecting the YAML frontmatter and content structure.~~ (Completed)
-   [x] ~~**Task 3.5: Rebuild Vector Database on Startup** - Ensure the backend automatically rebuilds the vector and keyword search databases when it starts up, loading all content from the `data/` directory.~~ (Completed)

### Task 4: Frontend and User Experience

- **[ ] 任务 4.1: 提供文件上传接口**
- **[ ] 任务 4.2: 支持更多文件格式**

### Task 5: User Experience Enhancement

- **[ ] 任务 5.1: 对话历史记录**
- **[ ] 任务 5.2: 用户反馈机制**

---

## A (Action / 行动) - 下一步做什么？

我们将严格按照计划的新顺序执行，首先解决内容来源的核心问题。

**当前行动项:**
- **执行任务 3.1: 开发Wiki内容抓取脚本**
- **具体步骤:**
    1.  为项目添加 `requests`, `beautifulsoup4`, `html2text` 等新的Python依赖。
    2.  创建 `scripts/` 目录，并在其中新建 `scrape_wiki.py` 文件。
    3.  编写初步的脚本代码，实现根据URL获取网页HTML的功能。

我们现在就开始着手第一步：**创建 `KNOWLEDGE_BASE_SPEC.md` 文件**。 