import axios, { type AxiosInstance, type AxiosRequestConfig } from 'axios'
import { authService } from './authService'

/**
 * 创建HTTP客户端实例
 */
const createHttpClient = (): AxiosInstance => {
  const client = axios.create({
    baseURL: `${window.location.protocol}//${window.location.host}/api`,
    timeout: 30000,
    headers: {
      'Content-Type': 'application/json',
    },
  })

  /**
   * 请求拦截器 - 添加认证token
   */
  client.interceptors.request.use(
    (config) => {
      const token = authService.getToken()
      if (token) {
        config.headers.Authorization = `Bearer ${token}`
      }
      return config
    },
    (error) => {
      return Promise.reject(error)
    }
  )

  /**
   * 响应拦截器 - 处理错误和token刷新
   */
  client.interceptors.response.use(
    (response) => {
      return response
    },
    async (error) => {
      const originalRequest = error.config

      // 如果是401错误且还没有重试过，尝试刷新token
      if (error.response?.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true

        try {
          const newToken = await authService.refreshToken()
          originalRequest.headers.Authorization = `Bearer ${newToken}`
          return client(originalRequest)
        } catch (refreshError) {
          // Token刷新失败，清除认证信息
          authService.clearAuth()
          // 重定向到登录页面
          window.location.href = '/login'
          return Promise.reject(refreshError)
        }
      }

      return Promise.reject(error)
    }
  )

  return client
}

export const httpClient = createHttpClient()

/**
 * 便捷方法
 */
export const http = {
  get: <T = any>(url: string, config?: AxiosRequestConfig) =>
    httpClient.get<T>(url, config),

  post: <T = any>(url: string, data?: any, config?: AxiosRequestConfig) =>
    httpClient.post<T>(url, data, config),

  put: <T = any>(url: string, data?: any, config?: AxiosRequestConfig) =>
    httpClient.put<T>(url, data, config),

  delete: <T = any>(url: string, config?: AxiosRequestConfig) =>
    httpClient.delete<T>(url, config),

  patch: <T = any>(url: string, data?: any, config?: AxiosRequestConfig) =>
    httpClient.patch<T>(url, data, config),
}
