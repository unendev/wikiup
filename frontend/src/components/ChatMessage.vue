<script setup lang="ts">
import { computed } from 'vue';
import { marked } from 'marked';

// 定义组件的属性
const props = defineProps<{
  message: {
    role: 'user' | 'ai';
    content: string;
    sources?: Array<any>; // 使用更通用的类型
  }
}>();

// 解析markdown内容为HTML
const renderedContent = computed(() => {
  return marked(props.message.content);
});

// 根据消息角色确定CSS类
const isUser = computed(() => props.message.role === 'user');

// 检查源信息格式是否有效
const hasValidSources = computed(() => {
  return props.message.sources && 
         props.message.sources.length > 0 && 
         typeof props.message.sources[0] === 'object';
});

// 获取源文件名
const getSourceFileName = (source: any): string => {
  if (source.source_file) return source.source_file;
  if (source.metadata?.source_file) return source.metadata.source_file;
  if (source.source) return source.source;
  if (source.path) return source.path;
  return '未知来源';
};

// 获取章节标题
const getSourceSection = (source: any): string => {
  if (source.section) return source.section;
  if (source.metadata?.section_headings) return source.metadata.section_headings;
  if (source.title) return source.title;
  return '未知章节';
};

// 获取内容文本
const getSourceContent = (source: any): string => {
  if (source.content) return source.content;
  if (source.text) return source.text;
  return '';
};

// 获取相似度分数
const getSourceScore = (source: any): string => {
  if (source.score) return Number(source.score).toFixed(2);
  return 'N/A';
};
</script>

<template>
  <div class="flex items-start space-x-3" :class="{ 'justify-end': isUser }">
    <!-- AI头像 -->
    <div v-if="!isUser" class="flex-shrink-0 w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center">
      <span class="text-xl">🤖</span>
    </div>

    <!-- 消息主体 -->
    <div class="max-w-2xl">
      <!-- 内容气泡 -->
      <div 
        class="p-3 rounded-lg shadow"
        :class="{
          'bg-white dark:bg-gray-700': !isUser,
          'bg-blue-500 text-white': isUser
        }"
      >
        <div class="prose prose-sm dark:prose-invert max-w-none" v-html="renderedContent"></div>
      </div>

      <!-- 来源信息（仅AI消息） -->
      <div v-if="!isUser && hasValidSources" class="mt-2 text-xs text-gray-500 dark:text-gray-400">
        <h4 class="font-bold mb-1">参考资料:</h4>
        <div class="space-y-2">
          <div v-for="(source, index) in message.sources" :key="index" class="bg-gray-100 dark:bg-gray-800 p-2 rounded">
            <p class="font-mono text-xs truncate" :title="getSourceFileName(source)">
              <strong>来源文件:</strong> {{ getSourceFileName(source) }}
            </p>
            <p class="font-mono text-xs">
              <strong>章节:</strong> {{ getSourceSection(source) }}
            </p>
            <p class="font-mono text-xs">
              <strong>相关度:</strong> {{ getSourceScore(source) }}
            </p>
            <details class="mt-1">
              <summary class="cursor-pointer text-gray-400">显示内容</summary>
              <p class="text-xs italic mt-1 p-1 bg-gray-200 dark:bg-gray-700 rounded">
                {{ getSourceContent(source) }}
              </p>
            </details>
          </div>
        </div>
      </div>
    </div>

    <!-- 用户头像 -->
    <div v-if="isUser" class="flex-shrink-0 w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center">
      <span class="text-xl">🧑</span>
    </div>
  </div>
</template>

<style>
/* 为prose类添加样式以正确渲染markdown HTML */
.prose ul {
  list-style-type: disc;
  padding-left: 1.5rem;
}
.prose ol {
  list-style-type: decimal;
  padding-left: 1.5rem;
}
</style> 