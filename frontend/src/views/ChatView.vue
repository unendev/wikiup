<script setup lang="ts">
import { ref, onMounted, nextTick, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/authStore'
import ChatMessage from '../components/ChatMessage.vue'

interface Message {
  role: 'user' | 'ai'
  content: string
  sources?: Array<any>
}

const router = useRouter()
const authStore = useAuthStore()

// Reactive state
const messages = ref<Message[]>([])
const userInput = ref('')
const isLoading = ref(false)
const chatContainer = ref<HTMLElement | null>(null)
const socket = ref<WebSocket | null>(null)

// Computed
const username = computed(() => authStore.username)
const isAdmin = computed(() => authStore.isAdmin)

// 初始化WebSocket连接
const initWebSocket = () => {
  if (authStore.token) {
    const wsUrl = `ws://${window.location.host}/api/v1/qa/ask?token=${authStore.token}`
    socket.value = new WebSocket(wsUrl)

    socket.value.onopen = () => {
      console.log('WebSocket connection established')
      messages.value.push({
        role: 'ai',
        content: '您好！我是您的《饥荒》游戏助手，有什么可以帮助您的吗？',
      })
    }

    socket.value.onmessage = (event) => {
      try {
        const response = JSON.parse(event.data)

        if (response.type === 'answer') {
          isLoading.value = false
          messages.value.push({
            role: 'ai',
            content: response.content,
            sources: response.sources || [],
          })
        } else if (response.type === 'error') {
          isLoading.value = false
          messages.value.push({
            role: 'ai',
            content: `错误: ${response.message}`,
          })
        } else if (response.type === 'chunk') {
          if (isLoading.value) {
            isLoading.value = false
            messages.value.push({
              role: 'ai',
              content: response.content,
            })
          } else {
            const lastMessage = messages.value[messages.value.length - 1]
            if (lastMessage && lastMessage.role === 'ai') {
              lastMessage.content += response.content
            }
          }
        } else if (response.type === 'done') {
          console.log('Streaming response completed')
          if (response.sources && response.sources.length > 0) {
            const lastMessage = messages.value[messages.value.length - 1]
            if (lastMessage && lastMessage.role === 'ai') {
              lastMessage.sources = response.sources
            }
          }
        }
      } catch (e) {
        const textResponse = event.data
        if (isLoading.value) {
          isLoading.value = false
          messages.value.push({
            role: 'ai',
            content: textResponse,
          })
        } else {
          const lastMessage = messages.value[messages.value.length - 1]
          if (lastMessage && lastMessage.role === 'ai') {
            lastMessage.content += textResponse
          }
        }
      }
      scrollToBottom()
    }

    socket.value.onclose = (event) => {
      console.log('WebSocket connection closed:', event)
      if (event.code === 1006) {
        messages.value.push({
          role: 'ai',
          content: '抱歉，连接已断开。这可能是由于认证失败或服务器问题。请刷新页面重试。',
        })
      } else {
        messages.value.push({
          role: 'ai',
          content: '连接已关闭。',
        })
      }
      isLoading.value = false
    }

    socket.value.onerror = (error) => {
      console.error('WebSocket error:', error)
      messages.value.push({
        role: 'ai',
        content: '抱歉，连接出错了，请稍后再试。',
      })
      isLoading.value = false
    }
  }
}

// 处理登出
const handleLogout = async () => {
  await authStore.logout()
  if (socket.value) {
    socket.value.close()
  }
  router.push('/login')
}

// 处理进入管理后台
const goToAdmin = () => {
  router.push('/admin')
}

// 滚动到底部
const scrollToBottom = async () => {
  await nextTick()
  if (chatContainer.value) {
    chatContainer.value.scrollTop = chatContainer.value.scrollHeight
  }
}

// 发送消息
const sendMessage = () => {
  const userText = userInput.value.trim()
  if (userText === '' || isLoading.value) return

  messages.value.push({
    role: 'user',
    content: userText,
  })
  userInput.value = ''
  scrollToBottom()

  isLoading.value = true

  if (socket.value && socket.value.readyState === WebSocket.OPEN) {
    socket.value.send(
      JSON.stringify({
        question: userText,
        stream: false,
      })
    )
  } else {
    messages.value.push({
      role: 'ai',
      content: '抱歉，WebSocket 连接不可用。请确认已登录。',
    })
    isLoading.value = false
  }
}

// 生命周期
onMounted(() => {
  // 检查认证状态
  if (!authStore.isAuthenticated) {
    router.push('/login')
    return
  }

  // 初始化WebSocket
  initWebSocket()
})
</script>

<template>
  <div class="flex flex-col h-screen bg-gray-100 dark:bg-gray-800">
    <!-- Header -->
    <header class="bg-white dark:bg-gray-900 shadow-md p-4">
      <div class="max-w-6xl mx-auto flex justify-between items-center">
        <div class="flex items-center gap-4">
          <h1 class="text-2xl font-bold text-gray-800 dark:text-white">
            WikiUp
          </h1>
          <span class="text-sm text-gray-600 dark:text-gray-400">
            欢迎, {{ username }}
          </span>
        </div>

        <div class="flex items-center gap-4">
          <!-- Admin Button -->
          <button
            v-if="isAdmin"
            @click="goToAdmin"
            class="px-4 py-2 bg-purple-500 hover:bg-purple-600 text-white rounded-lg transition"
            title="进入管理后台"
          >
            🔧 管理
          </button>

          <!-- Logout Button -->
          <button
            @click="handleLogout"
            class="px-4 py-2 bg-red-500 hover:bg-red-600 text-white rounded-lg transition"
          >
            退出
          </button>
        </div>
      </div>
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
                思考中...
              </p>
            </div>
          </div>
        </div>
      </div>
    </main>

    <!-- Chat Input -->
    <footer class="bg-white dark:bg-gray-900 p-4 border-t border-gray-200 dark:border-gray-700">
      <div class="max-w-4xl mx-auto">
        <form @submit.prevent="sendMessage" class="flex items-center gap-2">
          <input
            v-model="userInput"
            :disabled="isLoading"
            type="text"
            placeholder="输入您的问题..."
            class="flex-1 p-3 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50 dark:bg-gray-700 text-gray-900 dark:text-gray-100 disabled:opacity-50"
          />
          <button
            type="submit"
            :disabled="isLoading"
            class="px-6 py-3 bg-blue-500 hover:bg-blue-600 disabled:bg-blue-300 text-white rounded-lg transition font-semibold"
          >
            发送
          </button>
        </form>
      </div>
    </footer>
  </div>
</template>

<style scoped>
/* 可以添加额外的样式 */
</style>
