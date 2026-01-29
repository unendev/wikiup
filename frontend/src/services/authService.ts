import type { LoginRequest, LoginResponse, User, ApiResponse } from '../types/auth'

const API_BASE_URL = `${window.location.protocol}//${window.location.host}`
const AUTH_API = `${API_BASE_URL}/api/auth`
const TOKEN_KEY = 'authToken'
const USER_KEY = 'authUser'

/**
 * 认证服务 - 处理所有认证相关的API调用
 */
export const authService = {
  /**
   * 用户登录
   */
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    try {
      const response = await fetch(`${AUTH_API}/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      })

      if (!response.ok) {
        const error = await response.json()
        throw new Error(error.message || '登录失败')
      }

      const data: ApiResponse<LoginResponse> = await response.json()
      
      if (data.code !== 0) {
        throw new Error(data.message || '登录失败')
      }

      // 保存token和用户信息
      this.setToken(data.data.token)
      this.setUser(data.data.user)

      return data.data
    } catch (error) {
      console.error('Login error:', error)
      throw error
    }
  },

  /**
   * 用户注册
   */
  async register(credentials: LoginRequest & { email?: string }): Promise<LoginResponse> {
    try {
      const response = await fetch(`${AUTH_API}/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      })

      if (!response.ok) {
        const error = await response.json()
        throw new Error(error.message || '注册失败')
      }

      const data: ApiResponse<LoginResponse> = await response.json()
      
      if (data.code !== 0) {
        throw new Error(data.message || '注册失败')
      }

      // 自动登录
      this.setToken(data.data.token)
      this.setUser(data.data.user)

      return data.data
    } catch (error) {
      console.error('Register error:', error)
      throw error
    }
  },

  /**
   * 用户登出
   */
  async logout(): Promise<void> {
    try {
      const token = this.getToken()
      if (token) {
        await fetch(`${AUTH_API}/logout`, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        })
      }
    } catch (error) {
      console.error('Logout error:', error)
    } finally {
      this.clearAuth()
    }
  },

  /**
   * 刷新token
   */
  async refreshToken(): Promise<string> {
    try {
      const token = this.getToken()
      if (!token) {
        throw new Error('No token found')
      }

      const response = await fetch(`${AUTH_API}/refresh`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      })

      if (!response.ok) {
        throw new Error('Token refresh failed')
      }

      const data: ApiResponse<{ token: string }> = await response.json()
      
      if (data.code !== 0) {
        throw new Error(data.message || 'Token refresh failed')
      }

      this.setToken(data.data.token)
      return data.data.token
    } catch (error) {
      console.error('Token refresh error:', error)
      this.clearAuth()
      throw error
    }
  },

  /**
   * 获取当前用户信息
   */
  async getCurrentUser(): Promise<User> {
    try {
      const token = this.getToken()
      if (!token) {
        throw new Error('No token found')
      }

      const response = await fetch(`${AUTH_API}/me`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      })

      if (!response.ok) {
        throw new Error('Failed to fetch user info')
      }

      const data: ApiResponse<User> = await response.json()
      
      if (data.code !== 0) {
        throw new Error(data.message || 'Failed to fetch user info')
      }

      this.setUser(data.data)
      return data.data
    } catch (error) {
      console.error('Get current user error:', error)
      this.clearAuth()
      throw error
    }
  },

  /**
   * 验证token是否有效
   */
  async validateToken(): Promise<boolean> {
    try {
      const token = this.getToken()
      if (!token) {
        return false
      }

      const response = await fetch(`${AUTH_API}/validate`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      })

      return response.ok
    } catch (error) {
      console.error('Token validation error:', error)
      return false
    }
  },

  /**
   * 本地存储操作
   */
  setToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token)
  },

  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY)
  },

  setUser(user: User): void {
    localStorage.setItem(USER_KEY, JSON.stringify(user))
  },

  getUser(): User | null {
    const user = localStorage.getItem(USER_KEY)
    return user ? JSON.parse(user) : null
  },

  clearAuth(): void {
    localStorage.removeItem(TOKEN_KEY)
    localStorage.removeItem(USER_KEY)
  },

  /**
   * 检查用户是否有特定权限
   */
  hasRole(role: string): boolean {
    const user = this.getUser()
    return user ? user.roles.includes(role as any) : false
  },

  /**
   * 检查用户是否是管理员
   */
  isAdmin(): boolean {
    return this.hasRole('ADMIN')
  },

  /**
   * 获取授权header
   */
  getAuthHeader(): Record<string, string> {
    const token = this.getToken()
    return token ? { 'Authorization': `Bearer ${token}` } : {}
  },
}
