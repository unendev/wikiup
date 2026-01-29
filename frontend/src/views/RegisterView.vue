<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/authStore'

const router = useRouter()
const authStore = useAuthStore()

// Form state
const username = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const showPassword = ref(false)
const showConfirmPassword = ref(false)
const agreeTerms = ref(false)

// UI state
const isLoading = computed(() => authStore.loading)
const errorMessage = computed(() => authStore.error)

// Validation
const passwordsMatch = computed(() => password.value === confirmPassword.value)
const isPasswordValid = computed(() => password.value.length >= 6)
const isFormValid = computed(() => {
  return (
    username.value.trim().length >= 3 &&
    email.value.includes('@') &&
    isPasswordValid.value &&
    passwordsMatch.value &&
    agreeTerms.value
  )
})

// 处理注册
const handleRegister = async () => {
  if (!isFormValid.value) {
    authStore.error = '请填写所有必填项并同意条款'
    return
  }

  try {
    await authStore.register({
      username: username.value,
      email: email.value,
      password: password.value,
    })

    // 注册成功，重定向到首页
    router.push('/')
  } catch (error) {
    console.error('Register failed:', error)
  }
}

// 处理回车键
const handleKeydown = (event: KeyboardEvent) => {
  if (event.key === 'Enter' && !isLoading.value && isFormValid.value) {
    handleRegister()
  }
}

// 清除错误信息
const clearError = () => {
  authStore.clearError()
}
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-500 to-purple-600 flex items-center justify-center p-4">
    <div class="w-full max-w-md">
      <!-- Logo/Title -->
      <div class="text-center mb-8">
        <h1 class="text-4xl font-bold text-white mb-2">WikiUp</h1>
        <p class="text-blue-100">饥荒游戏知识库助手</p>
      </div>

      <!-- Register Card -->
      <div class="bg-white rounded-lg shadow-2xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">创建账户</h2>

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
        <form @submit.prevent="handleRegister" @keydown="handleKeydown" class="space-y-4">
          <!-- Username Field -->
          <div>
            <label for="username" class="block text-sm font-medium text-gray-700 mb-2">
              用户名 (至少3个字符)
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
            <p v-if="username.length > 0 && username.length < 3" class="text-red-500 text-xs mt-1">
              用户名至少需要3个字符
            </p>
          </div>

          <!-- Email Field -->
          <div>
            <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
              邮箱
            </label>
            <input
              id="email"
              v-model="email"
              type="email"
              placeholder="请输入邮箱地址"
              :disabled="isLoading"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent disabled:bg-gray-100 disabled:cursor-not-allowed transition"
              required
            />
          </div>

          <!-- Password Field -->
          <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
              密码 (至少6个字符)
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
            <p v-if="password.length > 0 && !isPasswordValid" class="text-red-500 text-xs mt-1">
              密码至少需要6个字符
            </p>
          </div>

          <!-- Confirm Password Field -->
          <div>
            <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-2">
              确认密码
            </label>
            <div class="relative">
              <input
                id="confirmPassword"
                v-model="confirmPassword"
                :type="showConfirmPassword ? 'text' : 'password'"
                placeholder="请再次输入密码"
                :disabled="isLoading"
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent disabled:bg-gray-100 disabled:cursor-not-allowed transition"
                required
              />
              <button
                type="button"
                @click="showConfirmPassword = !showConfirmPassword"
                class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
              >
                {{ showConfirmPassword ? '隐藏' : '显示' }}
              </button>
            </div>
            <p v-if="confirmPassword.length > 0 && !passwordsMatch" class="text-red-500 text-xs mt-1">
              两次输入的密码不一致
            </p>
          </div>

          <!-- Terms Agreement -->
          <div class="flex items-start gap-2">
            <input
              id="agreeTerms"
              v-model="agreeTerms"
              type="checkbox"
              class="w-4 h-4 text-blue-500 border-gray-300 rounded focus:ring-2 focus:ring-blue-500 mt-1"
            />
            <label for="agreeTerms" class="text-sm text-gray-600">
              我同意
              <a href="#" class="text-blue-500 hover:text-blue-600">服务条款</a>
              和
              <a href="#" class="text-blue-500 hover:text-blue-600">隐私政策</a>
            </label>
          </div>

          <!-- Submit Button -->
          <button
            type="submit"
            :disabled="isLoading || !isFormValid"
            class="w-full bg-blue-500 hover:bg-blue-600 disabled:bg-blue-300 text-white font-semibold py-2 px-4 rounded-lg transition duration-200 flex items-center justify-center gap-2"
          >
            <span v-if="isLoading" class="inline-block animate-spin">⏳</span>
            {{ isLoading ? '注册中...' : '注册' }}
          </button>
        </form>

        <!-- Divider -->
        <div class="my-6 flex items-center gap-4">
          <div class="flex-1 h-px bg-gray-300"></div>
          <span class="text-gray-500 text-sm">或</span>
          <div class="flex-1 h-px bg-gray-300"></div>
        </div>

        <!-- Login Link -->
        <div class="text-center">
          <p class="text-gray-600 text-sm">
            已有账户？
            <router-link
              to="/login"
              class="text-blue-500 hover:text-blue-600 font-semibold"
            >
              立即登录
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
