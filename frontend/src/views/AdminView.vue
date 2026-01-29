<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/authStore'

const router = useRouter()
const authStore = useAuthStore()

// Tab state
const activeTab = ref<'users' | 'knowledge' | 'settings'>('users')

// Users management
const users = ref<any[]>([])
const loadingUsers = ref(false)

// Knowledge base management
const documents = ref<any[]>([])
const loadingDocs = ref(false)

// Settings
const settings = ref({
  maxTokens: 2000,
  temperature: 0.7,
  topK: 5,
})

// 检查权限
const checkAdminAccess = () => {
  if (!authStore.isAdmin) {
    router.push('/')
  }
}

// 加载用户列表
const loadUsers = async () => {
  loadingUsers.value = true
  try {
    // TODO: 调用后端API获取用户列表
    // const response = await fetch('/api/admin/users', {
    //   headers: authService.getAuthHeader()
    // })
    // users.value = await response.json()
    users.value = [
      { id: '1', username: 'admin', email: 'admin@example.com', roles: ['ADMIN'], createdAt: '2025-01-01' },
      { id: '2', username: 'user1', email: 'user1@example.com', roles: ['USER'], createdAt: '2025-01-02' },
    ]
  } catch (error) {
    console.error('Failed to load users:', error)
  } finally {
    loadingUsers.value = false
  }
}

// 加载知识库
const loadDocuments = async () => {
  loadingDocs.value = true
  try {
    // TODO: 调用后端API获取文档列表
    documents.value = [
      { id: '1', title: '饥荒基础指南', status: 'processed', chunks: 150, createdAt: '2025-01-01' },
      { id: '2', title: '角色详解', status: 'processing', chunks: 0, createdAt: '2025-01-02' },
    ]
  } catch (error) {
    console.error('Failed to load documents:', error)
  } finally {
    loadingDocs.value = false
  }
}

// 删除用户
const deleteUser = async (userId: string) => {
  if (confirm('确定要删除此用户吗？')) {
    try {
      // TODO: 调用后端API删除用户
      users.value = users.value.filter(u => u.id !== userId)
    } catch (error) {
      console.error('Failed to delete user:', error)
    }
  }
}

// 删除文档
const deleteDocument = async (docId: string) => {
  if (confirm('确定要删除此文档吗？')) {
    try {
      // TODO: 调用后端API删除文档
      documents.value = documents.value.filter(d => d.id !== docId)
    } catch (error) {
      console.error('Failed to delete document:', error)
    }
  }
}

// 保存设置
const saveSettings = async () => {
  try {
    // TODO: 调用后端API保存设置
    alert('设置已保存')
  } catch (error) {
    console.error('Failed to save settings:', error)
  }
}

// 返回聊天
const backToChat = () => {
  router.push('/')
}

onMounted(() => {
  checkAdminAccess()
  loadUsers()
  loadDocuments()
})
</script>

<template>
  <div class="min-h-screen bg-gray-100 dark:bg-gray-800">
    <!-- Header -->
    <header class="bg-white dark:bg-gray-900 shadow-md p-4">
      <div class="max-w-6xl mx-auto flex justify-between items-center">
        <h1 class="text-2xl font-bold text-gray-800 dark:text-white">
          管理后台
        </h1>
        <button
          @click="backToChat"
          class="px-4 py-2 bg-blue-500 hover:bg-blue-600 text-white rounded-lg transition"
        >
          返回聊天
        </button>
      </div>
    </header>

    <!-- Main Content -->
    <main class="max-w-6xl mx-auto p-4">
      <!-- Tabs -->
      <div class="flex gap-4 mb-6 border-b border-gray-300 dark:border-gray-700">
        <button
          @click="activeTab = 'users'"
          :class="[
            'px-4 py-2 font-semibold transition',
            activeTab === 'users'
              ? 'text-blue-500 border-b-2 border-blue-500'
              : 'text-gray-600 dark:text-gray-400 hover:text-gray-800 dark:hover:text-gray-200'
          ]"
        >
          👥 用户管理
        </button>
        <button
          @click="activeTab = 'knowledge'"
          :class="[
            'px-4 py-2 font-semibold transition',
            activeTab === 'knowledge'
              ? 'text-blue-500 border-b-2 border-blue-500'
              : 'text-gray-600 dark:text-gray-400 hover:text-gray-800 dark:hover:text-gray-200'
          ]"
        >
          📚 知识库管理
        </button>
        <button
          @click="activeTab = 'settings'"
          :class="[
            'px-4 py-2 font-semibold transition',
            activeTab === 'settings'
              ? 'text-blue-500 border-b-2 border-blue-500'
              : 'text-gray-600 dark:text-gray-400 hover:text-gray-800 dark:hover:text-gray-200'
          ]"
        >
          ⚙️ 系统设置
        </button>
      </div>

      <!-- Users Tab -->
      <div v-if="activeTab === 'users'" class="bg-white dark:bg-gray-900 rounded-lg shadow-md p-6">
        <h2 class="text-xl font-bold mb-4 text-gray-800 dark:text-white">用户管理</h2>

        <div v-if="loadingUsers" class="text-center py-8">
          <p class="text-gray-600 dark:text-gray-400">加载中...</p>
        </div>

        <div v-else class="overflow-x-auto">
          <table class="w-full text-sm">
            <thead class="bg-gray-100 dark:bg-gray-800">
              <tr>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">用户名</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">邮箱</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">角色</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">创建时间</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="user in users"
                :key="user.id"
                class="border-b border-gray-200 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-800"
              >
                <td class="px-4 py-2 text-gray-900 dark:text-gray-100">{{ user.username }}</td>
                <td class="px-4 py-2 text-gray-900 dark:text-gray-100">{{ user.email }}</td>
                <td class="px-4 py-2">
                  <span
                    v-for="role in user.roles"
                    :key="role"
                    class="inline-block mr-2 px-2 py-1 bg-blue-100 dark:bg-blue-900 text-blue-800 dark:text-blue-200 rounded text-xs"
                  >
                    {{ role }}
                  </span>
                </td>
                <td class="px-4 py-2 text-gray-600 dark:text-gray-400">{{ user.createdAt }}</td>
                <td class="px-4 py-2">
                  <button
                    @click="deleteUser(user.id)"
                    class="text-red-500 hover:text-red-700 text-sm"
                  >
                    删除
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Knowledge Base Tab -->
      <div v-if="activeTab === 'knowledge'" class="bg-white dark:bg-gray-900 rounded-lg shadow-md p-6">
        <h2 class="text-xl font-bold mb-4 text-gray-800 dark:text-white">知识库管理</h2>

        <div v-if="loadingDocs" class="text-center py-8">
          <p class="text-gray-600 dark:text-gray-400">加载中...</p>
        </div>

        <div v-else class="overflow-x-auto">
          <table class="w-full text-sm">
            <thead class="bg-gray-100 dark:bg-gray-800">
              <tr>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">标题</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">状态</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">块数</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">创建时间</th>
                <th class="px-4 py-2 text-left text-gray-700 dark:text-gray-300">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="doc in documents"
                :key="doc.id"
                class="border-b border-gray-200 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-800"
              >
                <td class="px-4 py-2 text-gray-900 dark:text-gray-100">{{ doc.title }}</td>
                <td class="px-4 py-2">
                  <span
                    :class="[
                      'px-2 py-1 rounded text-xs font-semibold',
                      doc.status === 'processed'
                        ? 'bg-green-100 dark:bg-green-900 text-green-800 dark:text-green-200'
                        : 'bg-yellow-100 dark:bg-yellow-900 text-yellow-800 dark:text-yellow-200'
                    ]"
                  >
                    {{ doc.status === 'processed' ? '已处理' : '处理中' }}
                  </span>
                </td>
                <td class="px-4 py-2 text-gray-900 dark:text-gray-100">{{ doc.chunks }}</td>
                <td class="px-4 py-2 text-gray-600 dark:text-gray-400">{{ doc.createdAt }}</td>
                <td class="px-4 py-2">
                  <button
                    @click="deleteDocument(doc.id)"
                    class="text-red-500 hover:text-red-700 text-sm"
                  >
                    删除
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Settings Tab -->
      <div v-if="activeTab === 'settings'" class="bg-white dark:bg-gray-900 rounded-lg shadow-md p-6">
        <h2 class="text-xl font-bold mb-6 text-gray-800 dark:text-white">系统设置</h2>

        <div class="space-y-6 max-w-md">
          <!-- Max Tokens -->
          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              最大Token数
            </label>
            <input
              v-model.number="settings.maxTokens"
              type="number"
              min="100"
              max="4000"
              class="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50 dark:bg-gray-800 text-gray-900 dark:text-gray-100"
            />
            <p class="text-xs text-gray-500 dark:text-gray-400 mt-1">
              控制生成响应的最大长度
            </p>
          </div>

          <!-- Temperature -->
          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              温度 (Temperature)
            </label>
            <input
              v-model.number="settings.temperature"
              type="range"
              min="0"
              max="1"
              step="0.1"
              class="w-full"
            />
            <p class="text-xs text-gray-500 dark:text-gray-400 mt-1">
              当前值: {{ settings.temperature }} (0=确定性, 1=随机性)
            </p>
          </div>

          <!-- Top K -->
          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Top K (检索结果数)
            </label>
            <input
              v-model.number="settings.topK"
              type="number"
              min="1"
              max="20"
              class="w-full px-4 py-2 border border-gray-300 dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50 dark:bg-gray-800 text-gray-900 dark:text-gray-100"
            />
            <p class="text-xs text-gray-500 dark:text-gray-400 mt-1">
              每次查询返回的最相关文档数
            </p>
          </div>

          <!-- Save Button -->
          <button
            @click="saveSettings"
            class="w-full px-4 py-2 bg-blue-500 hover:bg-blue-600 text-white rounded-lg font-semibold transition"
          >
            保存设置
          </button>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
/* 可以添加额外的样式 */
</style>
