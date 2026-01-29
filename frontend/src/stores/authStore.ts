import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authService } from '../services/authService'
import type { User, LoginRequest, UserRole } from '../types/auth'

export const useAuthStore = defineStore('auth', () => {
  // State
  const user = ref<User | null>(null)
  const token = ref<string | null>(null)
  const loading = ref(false)
  const error = ref<string | null>(null)

  // 初始化时从localStorage恢复状态
  const initializeAuth = () => {
    const storedToken = authService.getToken()
    const storedUser = authService.getUser()
    
    if (storedToken && storedUser) {
      token.value = storedToken
      user.value = storedUser
    }
  }

  // Computed
  const isAuthenticated = computed(() => !!token.value && !!user.value)
  
  const isAdmin = computed(() => {
    return user.value?.roles.includes('ADMIN' as UserRole) ?? false
  })

  const username = computed(() => user.value?.username ?? '')

  const userRoles = computed(() => user.value?.roles ?? [])

  // Actions
  const login = async (credentials: LoginRequest) => {
    loading.value = true
    error.value = null
    
    try {
      const response = await authService.login(credentials)
      token.value = response.token
      user.value = response.user
      return response
    } catch (err) {
      error.value = err instanceof Error ? err.message : '登录失败'
      throw err
    } finally {
      loading.value = false
    }
  }

  const register = async (credentials: LoginRequest & { email?: string }) => {
    loading.value = true
    error.value = null
    
    try {
      const response = await authService.register(credentials)
      token.value = response.token
      user.value = response.user
      return response
    } catch (err) {
      error.value = err instanceof Error ? err.message : '注册失败'
      throw err
    } finally {
      loading.value = false
    }
  }

  const logout = async () => {
    loading.value = true
    error.value = null
    
    try {
      await authService.logout()
      token.value = null
      user.value = null
    } catch (err) {
      error.value = err instanceof Error ? err.message : '登出失败'
      throw err
    } finally {
      loading.value = false
    }
  }

  const refreshToken = async () => {
    try {
      const newToken = await authService.refreshToken()
      token.value = newToken
      return newToken
    } catch (err) {
      error.value = err instanceof Error ? err.message : 'Token刷新失败'
      token.value = null
      user.value = null
      throw err
    }
  }

  const getCurrentUser = async () => {
    loading.value = true
    error.value = null
    
    try {
      const currentUser = await authService.getCurrentUser()
      user.value = currentUser
      return currentUser
    } catch (err) {
      error.value = err instanceof Error ? err.message : '获取用户信息失败'
      throw err
    } finally {
      loading.value = false
    }
  }

  const validateToken = async () => {
    try {
      const isValid = await authService.validateToken()
      if (!isValid) {
        token.value = null
        user.value = null
      }
      return isValid
    } catch (err) {
      console.error('Token validation error:', err)
      return false
    }
  }

  const hasRole = (role: string): boolean => {
    return user.value?.roles.includes(role as UserRole) ?? false
  }

  const clearError = () => {
    error.value = null
  }

  return {
    // State
    user,
    token,
    loading,
    error,

    // Computed
    isAuthenticated,
    isAdmin,
    username,
    userRoles,

    // Actions
    initializeAuth,
    login,
    register,
    logout,
    refreshToken,
    getCurrentUser,
    validateToken,
    hasRole,
    clearError,
  }
})
