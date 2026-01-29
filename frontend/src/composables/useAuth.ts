import { computed } from 'vue'
import { useAuthStore } from '../stores/authStore'

/**
 * 认证相关的组合式函数
 */
export const useAuth = () => {
  const authStore = useAuthStore()

  // 计算属性
  const isAuthenticated = computed(() => authStore.isAuthenticated)
  const isAdmin = computed(() => authStore.isAdmin)
  const user = computed(() => authStore.user)
  const username = computed(() => authStore.username)
  const userRoles = computed(() => authStore.userRoles)
  const isLoading = computed(() => authStore.loading)
  const error = computed(() => authStore.error)

  // 方法
  const login = (credentials: { username: string; password: string }) =>
    authStore.login(credentials)

  const register = (credentials: {
    username: string
    email?: string
    password: string
  }) => authStore.register(credentials)

  const logout = () => authStore.logout()

  const hasRole = (role: string): boolean => authStore.hasRole(role)

  const hasAnyRole = (roles: string[]): boolean =>
    roles.some((role) => authStore.hasRole(role))

  const hasAllRoles = (roles: string[]): boolean =>
    roles.every((role) => authStore.hasRole(role))

  const clearError = () => authStore.clearError()

  return {
    // State
    isAuthenticated,
    isAdmin,
    user,
    username,
    userRoles,
    isLoading,
    error,

    // Methods
    login,
    register,
    logout,
    hasRole,
    hasAnyRole,
    hasAllRoles,
    clearError,
  }
}
