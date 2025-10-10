<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue';
import ChatMessage from './components/ChatMessage.vue';
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
import 'highlight.js/styles/github-dark.css';

// Define the shape of a message
interface Message {
  role: 'user' | 'ai';
  content: string;
  sources?: Array<any>; // Using 'any' for now for simplicity
}

const API_URL = "http://127.0.0.1:8000/api/ask/v0.2";

const md = new MarkdownIt({
  highlight: function (str: string, lang: string) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return '<pre class="hljs"><code>' +
               hljs.highlight(str, { language: lang, ignoreIllegals: true }).value +
               '</code></pre>';
      } catch (__) {}
    }
    return ''; // use external default escaping
  }
});

// Reactive state for the conversation
const messages = ref<Message[]>([
  {
    role: 'ai',
    content: '你好！你可以问我任何关于知识库的问题。例如：牛有什么用？',
    sources: []
  },
  {
    role: 'user',
    content: '牛在发情期是什么样的？'
  },
  {
    role: 'ai',
    content: '在发情期，牛的尾巴会竖起，后端变成红色。此时它们会对任何靠近的生物表现出攻击性。\n\n- **来源:** `data/dont-starve/beefalo.md` > 发情期',
    sources: [
      {
        content: '在发情期，牛的尾巴会竖起，后端变成红色。此时它们会对任何靠近的生物表现出攻击性。',
        metadata: {
          source_file: 'data/dont-starve/beefalo.md',
          section_headings: ['牛 (Beefalo)', '发情期']
        }
      }
    ]
  }
]);

const userInput = ref('');
const isLoading = ref(false);
const chatContainer = ref<HTMLElement | null>(null);
const socket = ref<WebSocket | null>(null);

onMounted(() => {
  // Connect to the WebSocket server
  socket.value = new WebSocket(`ws://${window.location.host}/api/v1/qa/ask`);

  socket.value.onopen = () => {
    console.log('WebSocket connection established');
    messages.value.push({ role: 'ai', content: '您好！我是您的《饥荒》游戏助手，有什么可以帮助您的吗？' });
  };

  socket.value.onmessage = (event) => {
    try {
      // 尝试解析JSON响应
      const response = JSON.parse(event.data);
      
      // 处理不同类型的消息
      if (response.type === 'answer') {
        // 完整回答
        isLoading.value = false;
        messages.value.push({ 
          role: 'ai', 
          content: response.content,
          sources: response.sources || []
        });
      } else if (response.type === 'error') {
        // 错误消息
        isLoading.value = false;
        messages.value.push({ role: 'ai', content: `错误: ${response.message}` });
      } else if (response.type === 'chunk') {
        // 流式响应块
        if (isLoading.value) {
          // 第一个块，创建新消息
          isLoading.value = false;
          messages.value.push({ role: 'ai', content: response.content });
        } else {
          // 后续块，追加到最后一条消息
          const lastMessage = messages.value[messages.value.length - 1];
          if (lastMessage && lastMessage.role === 'ai') {
            lastMessage.content += response.content;
          }
        }
      } else if (response.type === 'done') {
        // 流式响应完成
        console.log('Streaming response completed');
        // 如果响应中包含sources信息，添加到最后一条消息
        if (response.sources && response.sources.length > 0) {
          const lastMessage = messages.value[messages.value.length - 1];
          if (lastMessage && lastMessage.role === 'ai') {
            lastMessage.sources = response.sources;
          }
        }
      }
    } catch (e) {
      // 如果不是JSON格式，按纯文本处理
      const textResponse = event.data;
      if (isLoading.value) {
        isLoading.value = false;
        messages.value.push({ role: 'ai', content: textResponse });
      } else {
        const lastMessage = messages.value[messages.value.length - 1];
        if (lastMessage && lastMessage.role === 'ai') {
          lastMessage.content += textResponse;
        }
      }
    }
    
    scrollToBottom();
  };

  socket.value.onclose = () => {
    console.log('WebSocket connection closed');
  };

  socket.value.onerror = (error) => {
    console.error('WebSocket error:', error);
    messages.value.push({ role: 'ai', content: '抱歉，连接出错了，请稍后再试。' });
    isLoading.value = false;
  };
});


const scrollToBottom = async () => {
  await nextTick();
  if (chatContainer.value) {
    chatContainer.value.scrollTop = chatContainer.value.scrollHeight;
  }
};

// Function to handle sending a message
const sendMessage = () => {
  const userText = userInput.value.trim();
  if (userText === '' || isLoading.value) return;

  // 1. Add user message and clear input
  messages.value.push({ role: 'user', content: userText });
  userInput.value = '';
  scrollToBottom();

  // 2. Set loading state
  isLoading.value = true;

  if (socket.value && socket.value.readyState === WebSocket.OPEN) {
    // 发送JSON格式消息，添加时间戳防止缓存
    socket.value.send(JSON.stringify({ 
      question: userText,
      stream: true,  // 启用流式响应
      timestamp: new Date().getTime() 
    }));
    
    // 不再添加空的AI消息，依靠loading状态显示
  } else {
    messages.value.push({ role: 'ai', content: '抱歉，WebSocket 连接不可用。' });
    isLoading.value = false;
  }
};

const renderMarkdown = (text: string) => {
  return md.render(text);
};
</script>

<template>
  <div class="flex flex-col h-screen bg-gray-100 dark:bg-gray-800">
    
    <!-- Header -->
    <header class="bg-white dark:bg-gray-900 shadow-md p-4">
      <h1 class="text-xl font-bold text-gray-800 dark:text-white text-center">
        Wiki-上位 V0.2
      </h1>
    </header>

    <!-- Chat Messages -->
    <main ref="chatContainer" class="flex-1 overflow-y-auto p-4">
      <div class="max-w-4xl mx-auto">
        <div class="space-y-6">
          <ChatMessage 
            v-for="(msg, index) in messages" 
            :key="index" 
            :message="msg"
          />
          <!-- Loading Indicator -->
          <div v-if="isLoading" class="flex items-start space-x-3">
             <div class="flex-shrink-0 w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center">
              <span class="text-xl">🤖</span>
            </div>
            <div class="bg-white dark:bg-gray-700 p-3 rounded-lg shadow">
              <p class="text-gray-800 dark:text-gray-200 animate-pulse">
                Thinking...
              </p>
            </div>
          </div>
        </div>
      </div>
    </main>

    <!-- Chat Input Form -->
    <footer class="bg-white dark:bg-gray-900 p-4">
      <div class="max-w-4xl mx-auto">
        <form @submit.prevent="sendMessage" class="flex items-center space-x-2">
          <input
            v-model="userInput"
            :disabled="isLoading"
            type="text"
            placeholder="Type your message..."
            class="flex-1 p-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50 dark:bg-gray-700 text-gray-900 dark:text-gray-100 disabled:opacity-50"
          />
          <button 
            type="submit" 
            :disabled="isLoading"
            class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:opacity-50"
          >
            Send
          </button>
        </form>
      </div>
    </footer>

  </div>
</template>

<style scoped>
/* Scoped styles can be added here if needed, but we prefer Tailwind for consistency. */
</style>
