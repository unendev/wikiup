# WikiUp项目状态记录

## 项目概述
WikiUp是一个基于Spring Boot和Vue的知识库管理系统，使用向量数据库实现语义搜索功能。

## 当前进度
- [x] 后端基础框架搭建
- [x] 嵌入服务(EmbeddingService)实现
- [x] 向量数据库服务(VectorDBService)实现
- [x] 单元测试环境配置
- [x] WebSocket通信实现
- [x] 前端界面基础开发
- [x] 源文档追溯功能
- [x] 中文嵌入模型配置
- [x] 向量数据库磁盘化实现
- [x] 文本分块策略优化
- [ ] 用户认证与授权
- [ ] 知识库管理功能
- [ ] 部署与优化

## 技术栈
- **后端**：Spring Boot 2.7.x, Java 8
- **前端**：Vue.js 3, TypeScript, Tailwind CSS
- **数据存储**：文件系统(Markdown文件)、序列化向量存储
- **向量嵌入**：DJL + paraphrase-multilingual-MiniLM-L12-v2
- **通信**：WebSocket
- **LLM集成**：DeepSeek API


## 待解决问题

### 1. 知识库文件路径配置

**问题描述**：
当前知识库路径硬编码为`data/dst`，需要使其可配置并支持不同环境。

**待执行操作**：
- 完善配置文件中的路径设置
- 添加环境特定的配置文件
- 实现路径验证和自动创建目录功能

### 2. ~~文本分块策略优化~~ (已解决)

**问题描述**：
~~当前文本分块策略仅基于段落分割，缺乏语义完整性考虑。~~

**解决方案**：
- 实现了基于标题的分块策略，保持章节的语义完整性
- 为大章节创建子块时保留原标题，确保上下文连贯性
- 优化段落分割时的重叠策略，基于段落和句子边界
- 支持多级标题识别和分块

### 3. ~~大型知识库性能优化~~ (已解决)

**问题描述**：
~~当知识库规模扩大时，当前简单列表存储方式可能导致性能问题。~~

**解决方案**：
- 实现向量数据库的磁盘持久化，避免每次启动重新处理所有文档
- 添加处理进度显示，便于监控大规模文档处理过程
- 使用序列化机制保存和加载向量数据
- 维护向量元数据，确保来源追溯完整性

### 4. 知识库增量更新功能

**问题描述**：
当前向量库更新需要重新处理所有文档，效率较低。

**待执行操作**：
- 实现文件修改时间检测
- 添加增量更新机制，只处理新增或修改的文档
- 优化向量索引更新过程

## 下一步计划

1. **短期计划** (1周)
   - 实现知识库管理API
   - 实现知识库增量更新功能
   - 添加简单的用户认证机制

2. **中期计划** (2-3周)
   - 完善前端用户体验
   - 添加更多知识库格式支持
   - 实现向量索引优化

3. **长期计划** (1月)
   - 优化向量搜索性能
   - 实现知识库版本控制
   - 添加数据可视化功能

## 已解决问题

### 2025-07-22: 修复VectorDBService编译错误

**问题描述**：
在运行Maven测试时遇到编译错误，显示"class, interface, or enum expected"，错误位于`VectorDBService.java`文件的第27行。

**原因分析**：
1. 项目使用Java 8 (1.8)，但代码中使用了Java 14+的record类型特性
2. 使用了Jakarta EE的注解而不是JavaEE注解
3. 使用了Java 11的`Files.readString(Path)`方法

**解决方案**：
1. 将record类型转换为常规Java类
   - 创建了`VectorEntry`和`SearchResult`静态内部类
   - 添加了适当的字段、构造函数和访问器方法
2. 修复Java版本兼容性问题
   - 将`jakarta.annotation.PostConstruct`改为`javax.annotation.PostConstruct`
   - 将Java 11的`Files.readString(Path)`方法替换为Java 8兼容的`new String(Files.readAllBytes(file), StandardCharsets.UTF_8)`

**相关文件**：
- `backend/src/main/java/com/example/ragservice/service/VectorDBService.java`
- `backend/src/test/java/com/example/ragservice/service/VectorDBServiceTest.java`

### 2025-07-22: 解决WebSocket测试环境配置问题

**问题描述**：
在测试环境中运行测试时出现WebSocket相关错误：`javax.websocket.server.ServerContainer not available`

**原因分析**：
1. 测试环境中缺少WebSocket容器
2. 模型URL中包含非法字符导致初始化失败

**解决方案**：
1. 创建测试专用配置文件`application-test.properties`
   - 设置`spring.websocket.enabled=false`禁用WebSocket
   - 使用简化的模型URL避免解析错误
2. 修改WebSocketConfig类，使其在测试环境中不加载
   - 添加`@Profile("!test")`注解
   - 添加`@ConditionalOnProperty`条件
3. 创建TestEmbeddingConfig类，提供模拟的EmbeddingService
   - 使用Mockito模拟嵌入服务
   - 避免在测试环境中加载真实模型

**相关文件**：
- `backend/src/main/java/com/example/ragservice/config/WebSocketConfig.java`
- `backend/src/test/resources/application-test.properties`
- `backend/src/test/java/com/example/ragservice/config/TestEmbeddingConfig.java`
- `backend/src/test/java/com/example/ragservice/service/EmbeddingServiceTest.java`
- `backend/src/test/java/com/example/ragservice/service/VectorDBServiceTest.java`
- `backend/src/test/java/com/example/ragservice/service/LLMServiceTest.java`

### 2025-07-23: 实现源文档追溯和流式响应

**问题描述**：
1. 前端显示的来源信息不够详细，只显示一级标题
2. 前端不支持流式响应，用户体验不佳
3. 前端发送消息后会出现重复的气泡框

**解决方案**：
1. 改进源文档追溯功能
   - 在`VectorDBService`中添加`extractSectionTitle`方法，提取二级和三级标题
   - 在元数据中存储更详细的章节信息
   - 修改前端组件以正确显示章节信息
2. 实现流式响应
   - 修改前端WebSocket消息处理逻辑，支持流式响应
   - 在`ChatEndpoint`中添加流式响应完成后发送数据源的逻辑
3. 修复UI问题
   - 优化前端消息发送逻辑，避免重复气泡
   - 使用loading状态控制"Thinking..."气泡的显示

**相关文件**：
- `backend/src/main/java/com/example/ragservice/service/VectorDBService.java`
- `backend/src/main/java/com/example/ragservice/service/RAGService.java`
- `backend/src/main/java/com/example/ragservice/endpoint/ChatEndpoint.java`
- `frontend/src/App.vue`
- `frontend/src/components/ChatMessage.vue`

### 2025-07-24: 中文嵌入模型配置与相关度优化

**问题描述**：
需要配置多语言嵌入模型以提高中文查询效果

**解决方案**：
1. 配置了paraphrase-multilingual-MiniLM-L12-v2模型
   - 在`application.properties`中设置模型URL
   - 使用DJL加载和运行模型
2. 调整相关度阈值和搜索参数
   - 降低相似度阈值到0.2，提高召回率
   - 增加调试日志，显示文档相似度得分
   - 添加详细的章节信息到搜索结果

**相关文件**：
- `backend/src/main/resources/application.properties`
- `backend/src/main/java/com/example/ragservice/service/VectorDBService.java`
- `backend/src/main/java/com/example/ragservice/service/EmbeddingService.java`

### 2025-07-25: 向量数据库磁盘化与分块策略优化

**问题描述**：
1. 处理2000个md文件需要几个小时，每次启动都需要重新处理
2. 文本分块策略不够智能，导致语义分割不合理

**解决方案**：
1. 实现向量数据库磁盘化
   - 添加向量序列化和反序列化功能
   - 实现向量数据的磁盘存储和加载
   - 添加配置选项控制磁盘存储行为
   - 优化进度显示，便于监控处理过程
2. 优化分块策略
   - 实现基于标题的分块策略，保持章节完整性
   - 优化段落分割时的重叠处理，基于自然段落边界
   - 为大章节添加标题保持，确保上下文连贯
   - 添加句子级边界检测，提高分块质量

**相关文件**：
- `backend/src/main/java/com/example/ragservice/service/VectorDBService.java`
- `backend/src/main/resources/application.properties`

## 项目风险

1. **技术风险**：
   - Java 8环境限制了使用现代语言特性和API
   - WebSocket在某些环境下可能需要特殊配置

2. **功能风险**：
   - ~~大型知识库可能导致性能问题（缺少高效索引结构）~~ (已通过磁盘化缓解)
   - ~~文本分块策略不够智能，可能导致语义割裂~~ (已优化)

## 资源与文档

- [改造指南](./改造imp.md) - 项目改造和后续开发指南
- [API文档] - 待创建
- [用户手册] - 待创建

