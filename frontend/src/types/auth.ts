/**
 * 用户角色枚举
 */
export const UserRole = {
  ADMIN: 'ADMIN',
  USER: 'USER',
  GUEST: 'GUEST'
} as const

export type UserRole = typeof UserRole[keyof typeof UserRole]

/**
 * 用户信息接口
 */
export interface User {
  id: string
  username: string
  email?: string
  roles: UserRole[]
  createdAt?: string
  lastLogin?: string
}

/**
 * 登录请求
 */
export interface LoginRequest {
  username: string
  password: string
}

/**
 * 登录响应
 */
export interface LoginResponse {
  token: string
  user: User
  expiresIn: number
}

/**
 * 认证状态
 */
export interface AuthState {
  isAuthenticated: boolean
  user: User | null
  token: string | null
  loading: boolean
  error: string | null
}

/**
 * API响应包装
 */
export interface ApiResponse<T> {
  code: number
  message: string
  data: T
}
