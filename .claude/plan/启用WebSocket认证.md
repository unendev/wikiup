# 执行计划 - 为 WebSocket 端点启用认证

**目标**: 解决核心聊天功能未受认证保护的问题，将项目中已有的认证逻辑应用到 WebSocket 端点，并为前端提供基本的登录功能，使其成为一个更完整的“系统”。

**详细步骤**:

1.  **后端：保护 WebSocket 端点**
    *   **文件**: `backend/src/main/java/com/example/ragservice/config/SecurityConfig.java`
    *   **操作**: 在 `securityFilterChain` 方法中，移除 `.antMatchers("/api/v1/qa/ask").permitAll()` 这一行，使所有对该端点的请求都要求认证。

2.  **后端：从查询参数中提取令牌**
    *   **文件**: `backend/src/main/java/com/example/ragservice/security/JwtAuthenticationFilter.java`
    *   **操作**: 修改 `doFilterInternal` 方法，增加从 URL 查询参数 `token` 中提取 JWT 令牌的逻辑。

3.  **后端：将安全上下文传播到 WebSocket 会话**
    *   **文件**: `backend/src/main/java/com/example/ragservice/config/WebSocketConfig.java`
    *   **操作**: 创建一个名为 `SecurityAwareServerEndpointConfigurator` 的内部类，用于在 WebSocket 握手期间从 HTTP 请求中捕获用户身份 (`Principal`) 并将其存入 WebSocket 会话。
    *   **文件**: `backend/src/main/java/com/example/ragservice/endpoint/ChatEndpoint.java`
    *   **操作**: 修改 `@ServerEndpoint` 注解，应用上述创建的 `configurator`。

4.  **后端：在 ChatEndpoint 中使用认证信息**
    *   **文件**: `backend/src/main/java/com/example/ragservice/endpoint/ChatEndpoint.java`
    *   **操作**: 修改 `onMessage` 等方法，从 `session.getUserPrincipal()` 中获取认证后的用户信息，并将其传递给业务逻辑层。

5.  **前端：实现基本登录 UI 并发送认证令牌**
    *   **文件**: `frontend/src/App.vue`
    *   **操作**:
        1.  **实现一个简单的登录表单**：添加输入字段用于用户名和密码，以及一个登录按钮。当用户未认证时，将显示此表单。
        2.  **处理登录请求**：当点击登录按钮时，向后端 `/api/auth/login` 端点发送 POST 请求，包含用户名和密码。
        3.  **存储 JWT**：成功登录后，从后端响应中提取 JWT 令牌并安全地存储在 `localStorage` 中。
        4.  **条件渲染**：仅当 `localStorage` 中存在有效的 JWT 令牌时才显示聊天界面。否则，显示登录表单。
        5.  **修改 WebSocket 连接**：当令牌可用时，使用它建立 WebSocket 连接（将其作为查询参数传递）。
        6.  **注销功能**：添加一个简单的注销按钮，清除 `localStorage` 中的 JWT 并返回登录表单。
    *   **目的**：提供功能性的登录体验，允许用户进行认证，然后访问受保护的聊天功能，从而使前端成为一个更完整的“系统”。
    *   **预期结果**：前端将呈现一个登录屏幕，成功登录后，将过渡到聊天界面，并对 WebSocket 连接进行认证。