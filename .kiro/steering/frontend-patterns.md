---
inclusion: fileMatch
fileMatchPattern: 'frontend/**/*'
---

# Frontend Development Patterns

## Vue 3 Composition API Standards

### Component Structure
Always use `<script setup>` syntax with TypeScript:
```vue
<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import type { ComponentProps } from '../types'

// Props
const props = defineProps<ComponentProps>()

// Emits
const emit = defineEmits<{
  update: [value: string]
}>()

// State
const state = ref<string>('')

// Computed
const computed = computed(() => state.value.toUpperCase())

// Methods
const handleAction = () => {
  emit('update', state.value)
}

// Lifecycle
onMounted(() => {
  // initialization
})
</script>
```

## State Management with Pinia

### Store Pattern
Follow the composition API style:
```typescript
import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useMyStore = defineStore('myStore', () => {
  // State
  const data = ref<DataType | null>(null)
  const loading = ref(false)
  const error = ref<string | null>(null)

  // Computed
  const isReady = computed(() => !!data.value)

  // Actions
  const fetchData = async () => {
    loading.value = true
    error.value = null
    try {
      // fetch logic
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'Unknown error'
      throw err
    } finally {
      loading.value = false
    }
  }

  return { data, loading, error, isReady, fetchData }
})
```

## Router Configuration

### Route Meta Fields
- `requiresAuth`: boolean - requires authentication
- `requiresAdmin`: boolean - requires admin role
- `title`: string - page title

### Navigation Guards
Always validate authentication state in `beforeEach` guard and handle token validation.

## HTTP Client Standards

### Use httpClient Service
Import from `@/services/httpClient` for automatic:
- JWT token injection
- Token refresh on 401
- Error handling
- Request/response interceptors

### Error Handling
Always catch and handle errors with user-friendly messages:
```typescript
try {
  const response = await apiCall()
  return response.data
} catch (err) {
  const message = err instanceof Error ? err.message : 'Operation failed'
  // Handle error appropriately
  throw new Error(message)
}
```

## TypeScript Standards

### Type Definitions
- Define interfaces in `src/types/` directory
- Use explicit types for all function parameters and returns
- Avoid `any` type - use `unknown` if type is truly unknown
- Use type guards for runtime type checking

### Naming Conventions
- Components: PascalCase (e.g., `ChatView.vue`)
- Composables: camelCase with `use` prefix (e.g., `useAuth`)
- Types/Interfaces: PascalCase (e.g., `User`, `LoginRequest`)
- Constants: UPPER_SNAKE_CASE

## Styling with Tailwind

### Utility-First Approach
Prefer Tailwind utilities over custom CSS:
```vue
<div class="flex items-center justify-between p-4 bg-white rounded-lg shadow-md">
  <!-- content -->
</div>
```

### Responsive Design
Use responsive prefixes: `sm:`, `md:`, `lg:`, `xl:`, `2xl:`

## Component Best Practices

### Props Validation
Always define prop types explicitly:
```typescript
interface Props {
  title: string
  count?: number
  onUpdate?: (value: string) => void
}

const props = withDefaults(defineProps<Props>(), {
  count: 0
})
```

### Emit Events
Define emits with TypeScript:
```typescript
const emit = defineEmits<{
  submit: [data: FormData]
  cancel: []
}>()
```

### Composables
Extract reusable logic into composables in `src/composables/`:
```typescript
export function useFeature() {
  const state = ref()
  
  const action = () => {
    // logic
  }
  
  return { state, action }
}
```
