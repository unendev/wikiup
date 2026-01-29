<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push('/')
}

const goBack = () => {
  router.back()
}
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-500 to-purple-600 flex items-center justify-center p-4">
    <div class="text-center">
      <!-- 404 Icon -->
      <div class="text-9xl font-bold text-white mb-4 opacity-80">
        404
      </div>

      <!-- Message -->
      <h1 class="text-4xl font-bold text-white mb-2">
        页面未找到
      </h1>
      <p class="text-xl text-blue-100 mb-8">
        抱歉，您访问的页面不存在或已被移除。
      </p>

      <!-- Buttons -->
      <div class="flex gap-4 justify-center">
        <button
          @click="goHome"
          class="px-6 py-3 bg-white text-blue-600 font-semibold rounded-lg hover:bg-blue-50 transition"
        >
          返回首页
        </button>
        <button
          @click="goBack"
          class="px-6 py-3 bg-blue-400 text-white font-semibold rounded-lg hover:bg-blue-500 transition"
        >
          返回上一页
        </button>
      </div>

      <!-- Illustration -->
      <div class="mt-12 text-6xl">
        🔍
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 可以添加额外的样式 */
</style>
