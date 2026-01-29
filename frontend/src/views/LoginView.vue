<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '../stores/authStore'
import type { LoginRequest } from '../types/auth'

const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()

// Form state
const username = ref('')
const password = ref('')
const rememberMe = ref(false)
const showPassword = ref(false)

// UI state
const isLoading = computed(() => authStore.loading)
const errorMessage = computed(() => authStore.error)

// 从localStorage恢复记住的用户名
const initializeForm = () => {
  const savedUsername = localStorage.getItem('savedUsername')
  if (savedUsername) {
    username.value = savedUsername
    rememberMe.value = true
  }
}

// 处理登录
const handleLogin = async () => {
  if (!username.value || !password.value) {
    authStore.error = '请输入用户名和密码'
    return
  }

  try {
    const credentials: LoginRequest = {
      username: username.value,
      password: password.value,
    }

    await authStore.login(credentials)

    // 保存用户名（如果勾选了记住我）
    if (rememberMe.value) {
      localStorage.setItem('savedUsername', username.value)
    } else {
      localStorage.removeItem('savedUsername')
    }

    // 重定向到原页面或首页
    const redirect = route.query.redirect as string
    router.push(redirect || '/')
  } catch (error) {
    console.error('Login failed:', error)
  }
}

// 处理回车键
const handleKeydown = (event: KeyboardEvent) => {
  if (event.key === 'Enter' && !isLoading.value) {
    handleLogin()
  }
}

// 清除错误信息
const clearError = () => {
  authStore.clearError()
}

initializeForm()
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-500 to-purple-600 flex items-center justify-center p-4">
    <div class="w-full max-w-md">
      <!-- Logo/Title -->
      <div class="text-center mb-8">
        <h1 class="text-4xl font-bold text-white mb-2">WikiUp</h1>
        <p class="text-blue-100">饥荒游戏知识库助手</p>
      </div>

      <!-- Login Card -->
      <div class="bg-white rounded-lg shadow-2xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">登录</h2>

        <!-- Error Message -->
        <div
          v-if="errorMessage"
          class="mb-4 p-4 bg-red-50 border border-red-200 rounded-lg flex items-start gap-3"
        >
          <span class="text-red-500 text-xl flex-shrink-0">⚠️</span>
          <div class="flex-1">
            <p class="text-red-800 text-sm">{{ errorMessage }}</p>
            <button
              @click="clearError"
              class="text-red-600 hover:text-red-800 text-xs mt-1 underline"
            >
              关闭
            </button>
          </div>
        </div>

        <!-- Form -->
        <form @submit.prevent="handleLogin" @keydown="handleKeydown" class="space-y-4">
          <!-- Username Field -->
          <div>
            <label for="username" class="block text-sm font-medium text-gray-700 mb-2">
              用户名
            </label>
            <input
              id="username"
              v-model="username"
              type="text"
              placeholder="请输入用户名"
              :disabled="isLoading"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent disabled:bg-gray-100 disabled:cursor-not-allowed transition"
              required
            />
          </div>

          <!-- Password Field -->
          <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
              密码
            </label>
            <div class="relative">
              <input
                id="password"
                v-model="password"
                :type="showPassword ? 'text' : 'password'"
                placeholder="请输入密码"
                :disabled="isLoading"
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent disabled:bg-gray-100 disabled:cursor-not-allowed transition"
                required
              />
              <button
                type="button"
                @click="showPassword = !showPassword"
                class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
              >
                {{ showPassword ? '隐藏' : '显示' }}
              </button>
            </div>
          </div>

          <!-- Remember Me -->
          <div class="flex items-center">
            <input
              id="rememberMe"
              v-model="rememberMe"
              type="checkbox"
              class="w-4 h-4 text-blue-500 border-gray-300 rounded focus:ring-2 focus:ring-blue-500"
            />
            <label for="rememberMe" class="ml-2 text-sm text-gray-600">
              记住我
            </label>
          </div>

          <!-- Submit Button -->
          <button
            type="submit"
            :disabled="isLoading"
            class="w-full bg-blue-500 hover:bg-blue-600 disabled:bg-blue-300 text-white font-semibold py-2 px-4 rounded-lg transition duration-200 flex items-center justify-center gap-2"
          >
            <span v-if="isLoading" class="inline-block animate-spin">⏳</span>
            {{ isLoading ? '登录中...' : '登录' }}
          </button>
        </form>

        <!-- Divider -->
        <div class="my-6 flex items-center gap-4">
          <div class="flex-1 h-px bg-gray-300"></div>
          <span class="text-gray-500 text-sm">或</span>
          <div class="flex-1 h-px bg-gray-300"></div>
        </div>

        <!-- Register Link -->
        <div class="text-center">
          <p class="text-gray-600 text-sm">
            还没有账户？
            <router-link
              to="/register"
              class="text-blue-500 hover:text-blue-600 font-semibold"
            >
              立即注册
            </router-link>
          </p>
        </div>

        <!-- Footer -->
        <div class="mt-6 pt-6 border-t border-gray-200 text-center text-xs text-gray-500">
          <p>© 2025 WikiUp. 保留所有权利。</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以添加额外的样式 */
</style>
