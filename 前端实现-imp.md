# WikiUp前端实现方案 (RFC)

## 1. 概述

本文档描述了WikiUp项目前端的完善方案，重点关注用户认证与授权功能的实现，以及整体用户体验的提升。前端将基于Vue 3、TypeScript和Tailwind CSS，与后端已实现的Spring Security + JWT认证系统进行集成。

## 2. 目标

- 实现用户认证界面（登录、注册、登出）
- 集成JWT令牌管理
- 添加权限控制
- 改进聊天界面用户体验
- 添加管理员功能界面

## 3. 技术栈

- **框架**: Vue 3 + TypeScript
- **状态管理**: Pinia
- **UI组件**: Tailwind CSS + HeadlessUI
- **HTTP客户端**: Axios
- **路由**: Vue Router
- **表单验证**: Vee-Validate
- **WebSocket**: 原生WebSocket API

## 4. 架构设计

### 4.1 目录结构

```
frontend/
├── src/
│   ├── assets/            # 静态资源
│   ├── components/        # 通用组件
│   │   ├── auth/          # 认证相关组件
│   │   ├── chat/          # 聊天相关组件
│   │   └── common/        # 通用UI组件
│   ├── composables/       # 组合式函数
│   ├── layouts/           # 页面布局
│   ├── pages/             # 页面组件
│   ├── router/            # 路由配置
│   ├── services/          # API服务
│   ├── stores/            # Pinia状态
│   ├── types/             # TypeScript类型
│   ├── utils/             # 工具函数
│   ├── App.vue            # 根组件
│   ├── main.ts            # 入口文件
│   └── style.css          # 全局样式
├── .env                   # 环境变量
└── vite.config.ts         # Vite配置
```

### 4.2 认证流程

1. 用户输入凭据并提交登录表单
2. 前端向`/api/auth/signin`发送请求
3. 后端验证凭据并返回JWT令牌
4. 前端将令牌存储在localStorage/sessionStorage中
5. 后续API请求在Authorization头中携带令牌
6. WebSocket连接时在URL参数中携带令牌

### 4.3 路由设计

```typescript
const routes = [
  {
    path: '/',
    component: MainLayout,
    children: [
      { path: '', component: HomePage },
      { 
        path: 'chat', 
        component: ChatPage,
        meta: { requiresAuth: true }
      },
      { 
        path: 'admin', 
        component: AdminPage,
        meta: { requiresAuth: true, requiresAdmin: true }
      }
    ]
  },
  {
    path: '/auth',
    component: AuthLayout,
    children: [
      { path: 'login', component: LoginPage },
      { path: 'register', component: RegisterPage },
      { path: 'forgot-password', component: ForgotPasswordPage }
    ]
  }
];
```

## 5. 组件设计

### 5.1 认证组件

#### LoginForm.vue
```vue
<template>
  <form @submit.prevent="handleSubmit" class="space-y-6">
    <div>
      <label for="username" class="block text-sm font-medium text-gray-700">用户名</label>
      <input v-model="username" type="text" id="username" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
    </div>
    
    <div>
      <label for="password" class="block text-sm font-medium text-gray-700">密码</label>
      <input v-model="password" type="password" id="password" required class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
    </div>
    
    <div class="flex items-center">
      <input v-model="rememberMe" id="remember-me" type="checkbox" class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded">
      <label for="remember-me" class="ml-2 block text-sm text-gray-900">记住我</label>
    </div>
    
    <div>
      <button type="submit" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
        登录
      </button>
    </div>
    
    <div class="text-sm text-center">
      <router-link to="/auth/register" class="font-medium text-indigo-600 hover:text-indigo-500">
        没有账号？点击注册
      </router-link>
    </div>
  </form>
</template>
```

#### RegisterForm.vue
```vue
<template>
  <form @submit.prevent="handleSubmit" class="space-y-6">
    <!-- 用户名、邮箱、密码字段 -->
    <!-- 提交按钮 -->
  </form>
</template>
```

### 5.2 聊天组件改进

#### ChatContainer.vue
```vue
<template>
  <div class="flex flex-col h-full">
    <!-- 消息列表 -->
    <div class="flex-1 overflow-y-auto p-4 space-y-4" ref="messageList">
      <chat-message v-for="(message, index) in messages" :key="index" :message="message" />
    </div>
    
    <!-- 输入区域 -->
    <div class="border-t p-4">
      <div class="flex space-x-2">
        <input 
          v-model="userInput" 
          @keyup.enter="sendMessage" 
          type="text" 
          placeholder="输入您的问题..." 
          class="flex-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          :disabled="isLoading"
        />
        <button 
          @click="sendMessage" 
          class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          :disabled="isLoading"
        >
          发送
        </button>
      </div>
    </div>
  </div>
</template>
```

## 6. 状态管理

### 6.1 认证状态

```typescript
// stores/auth.ts
import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import AuthService from '@/services/AuthService';
import { User } from '@/types';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User | null>(null);
  const token = ref<string | null>(null);
  const loading = ref(false);
  const error = ref<string | null>(null);
  
  const isAuthenticated = computed(() => !!token.value);
  const isAdmin = computed(() => user.value?.roles.includes('ROLE_ADMIN') || false);
  
  async function login(username: string, password: string, rememberMe: boolean) {
    loading.value = true;
    error.value = null;
    
    try {
      const response = await AuthService.login(username, password, rememberMe);
      token.value = response.token;
      user.value = {
        id: response.id,
        username: response.username,
        email: response.email,
        roles: response.roles
      };
      
      localStorage.setItem('token', token.value);
      if (rememberMe) {
        localStorage.setItem('user', JSON.stringify(user.value));
      }
    } catch (err: any) {
      error.value = err.message || '登录失败';
    } finally {
      loading.value = false;
    }
  }
  
  function logout() {
    AuthService.logout();
    token.value = null;
    user.value = null;
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }
  
  function initialize() {
    const storedToken = localStorage.getItem('token');
    const storedUser = localStorage.getItem('user');
    
    if (storedToken) {
      token.value = storedToken;
      if (storedUser) {
        user.value = JSON.parse(storedUser);
      }
    }
  }
  
  return {
    user,
    token,
    loading,
    error,
    isAuthenticated,
    isAdmin,
    login,
    logout,
    initialize
  };
});
```

### 6.2 聊天状态

```typescript
// stores/chat.ts
import { defineStore } from 'pinia';
import { ref } from 'vue';
import { Message } from '@/types';

export const useChatStore = defineStore('chat', () => {
  const messages = ref<Message[]>([]);
  const isLoading = ref(false);
  const socket = ref<WebSocket | null>(null);
  
  // WebSocket连接与消息处理方法
  
  return {
    messages,
    isLoading,
    socket,
    // 导出方法
  };
});
```

## 7. API服务

### 7.1 认证服务

```typescript
// services/AuthService.ts
import axios from 'axios';

const API_URL = '/api/auth/';

class AuthService {
  async login(username: string, password: string, rememberMe: boolean) {
    const response = await axios.post(API_URL + 'signin', {
      username,
      password,
      rememberMe
    });
    return response.data;
  }
  
  async register(username: string, email: string, password: string) {
    const response = await axios.post(API_URL + 'signup', {
      username,
      email,
      password
    });
    return response.data;
  }
  
  logout() {
    return axios.post(API_URL + 'signout');
  }
  
  getCurrentUser() {
    return axios.get(API_URL + 'me');
  }
}

export default new AuthService();
```

### 7.2 HTTP拦截器

```typescript
// services/setupInterceptors.ts
import axios from 'axios';
import { useAuthStore } from '@/stores/auth';

export default function setupInterceptors() {
  axios.interceptors.request.use(
    (config) => {
      const authStore = useAuthStore();
      if (authStore.token) {
        config.headers['Authorization'] = `Bearer ${authStore.token}`;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );
  
  axios.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const originalRequest = error.config;
      
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        const authStore = useAuthStore();
        
        try {
          // 尝试刷新令牌
          const response = await axios.post('/api/auth/refresh');
          const newToken = response.data.token;
          
          authStore.token = newToken;
          localStorage.setItem('token', newToken);
          
          originalRequest.headers['Authorization'] = `Bearer ${newToken}`;
          return axios(originalRequest);
        } catch (refreshError) {
          // 刷新失败，登出用户
          authStore.logout();
          return Promise.reject(refreshError);
        }
      }
      
      return Promise.reject(error);
    }
  );
}
```

## 8. WebSocket认证集成

### 8.1 创建认证WebSocket

```typescript
// services/WebSocketService.ts
import { useAuthStore } from '@/stores/auth';

export default class WebSocketService {
  private socket: WebSocket | null = null;
  private messageHandlers: ((data: any) => void)[] = [];
  
  connect() {
    const authStore = useAuthStore();
    if (!authStore.token) {
      throw new Error('未授权，无法连接WebSocket');
    }
    
    // 在WebSocket URL中添加令牌
    const wsUrl = `ws://${window.location.host}/api/v1/qa/ask?token=${authStore.token}`;
    this.socket = new WebSocket(wsUrl);
    
    this.socket.onopen = () => {
      console.log('WebSocket连接已建立');
    };
    
    this.socket.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        this.messageHandlers.forEach(handler => handler(data));
      } catch (error) {
        console.error('WebSocket消息解析错误:', error);
      }
    };
    
    this.socket.onclose = () => {
      console.log('WebSocket连接已关闭');
    };
    
    this.socket.onerror = (error) => {
      console.error('WebSocket错误:', error);
    };
  }
  
  addMessageHandler(handler: (data: any) => void) {
    this.messageHandlers.push(handler);
  }
  
  sendMessage(message: any) {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    } else {
      console.error('WebSocket未连接');
    }
  }
  
  disconnect() {
    if (this.socket) {
      this.socket.close();
      this.socket = null;
    }
  }
}
```

## 9. 路由守卫

```typescript
// router/index.ts
import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

// 路由配置...

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  
  // 检查路由是否需要认证
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!authStore.isAuthenticated) {
      // 未认证，重定向到登录页
      next({
        path: '/auth/login',
        query: { redirect: to.fullPath }
      });
    } else {
      // 检查是否需要管理员权限
      if (to.matched.some(record => record.meta.requiresAdmin) && !authStore.isAdmin) {
        // 无管理员权限，重定向到首页
        next({ path: '/' });
      } else {
        next();
      }
    }
  } else {
    next();
  }
});
```

## 10. 实施计划

### 10.1 阶段一：基础认证 (1周)

1. 创建认证相关组件 (登录/注册)
2. 实现Pinia状态管理
3. 配置API服务和HTTP拦截器
4. 添加路由守卫

### 10.2 阶段二：聊天功能改进 (1周)

1. 重构现有聊天组件
2. 集成WebSocket认证
3. 改进消息展示和用户体验
4. 添加错误处理和重连机制

### 10.3 阶段三：管理功能 (1周)

1. 创建管理员界面
2. 实现知识库管理功能
3. 添加用户管理功能
4. 实现系统配置界面

## 11. 测试策略

1. **单元测试**: 使用Vitest测试关键组件和服务
2. **端到端测试**: 使用Cypress测试完整用户流程
3. **集成测试**: 测试前后端交互

## 12. 兼容性考虑

1. 支持现代浏览器 (Chrome, Firefox, Safari, Edge)
2. 响应式设计，支持桌面和移动设备
3. 优雅降级，处理WebSocket不可用情况

## 13. 安全考虑

1. 令牌存储在localStorage/sessionStorage中
2. 实现令牌刷新机制
3. 防止XSS和CSRF攻击
4. 敏感操作需要重新验证

## 14. 性能优化

1. 组件懒加载
2. 资源压缩和缓存
3. WebSocket消息批处理
4. 虚拟滚动处理大量消息

## 15. 结论

本实施方案提供了WikiUp前端完善的详细计划，重点关注用户认证与授权功能的实现。通过与后端已实现的Spring Security + JWT认证系统集成，可以构建一个安全、高效、用户友好的知识库管理系统。 