import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import { useAuthStore } from '../stores/authStore'

// 页面组件
import ChatView from '../views/ChatView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import AdminView from '../views/AdminView.vue'
import NotFoundView from '../views/NotFoundView.vue'

/**
 * 路由配置
 */
const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'Chat',
    component: ChatView,
    meta: {
      requiresAuth: true,
      title: 'WikiUp - 聊天',
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView,
    meta: {
      requiresAuth: false,
      title: 'WikiUp - 登录',
    },
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView,
    meta: {
      requiresAuth: false,
      title: 'WikiUp - 注册',
    },
  },
  {
    path: '/admin',
    name: 'Admin',
    component: AdminView,
    meta: {
      requiresAuth: true,
      requiresAdmin: true,
      title: 'WikiUp - 管理后台',
    },
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFoundView,
    meta: {
      title: 'WikiUp - 页面未找到',
    },
  },
]

/**
 * 创建路由实例
 */
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

/**
 * 全局路由守卫 - 权限检查
 */
router.beforeEach(async (to, from, next) => {
  const authStore = useAuthStore()

  // 初始化认证状态（仅在首次加载时）
  if (!authStore.user && !authStore.token) {
    authStore.initializeAuth()
  }

  // 验证token有效性
  if (authStore.token && !authStore.user) {
    try {
      const isValid = await authStore.validateToken()
      if (!isValid) {
        authStore.logout()
      }
    } catch (error) {
      console.error('Token validation failed:', error)
      authStore.logout()
    }
  }

  const requiresAuth = to.meta.requiresAuth as boolean
  const requiresAdmin = to.meta.requiresAdmin as boolean
  const isAuthenticated = authStore.isAuthenticated
  const isAdmin = authStore.isAdmin

  // 需要认证但未登录
  if (requiresAuth && !isAuthenticated) {
    next({
      name: 'Login',
      query: { redirect: to.fullPath },
    })
    return
  }

  // 需要管理员权限但不是管理员
  if (requiresAdmin && !isAdmin) {
    next({
      name: 'Chat',
    })
    return
  }

  // 已登录用户访问登录/注册页面，重定向到首页
  if ((to.name === 'Login' || to.name === 'Register') && isAuthenticated) {
    next({
      name: 'Chat',
    })
    return
  }

  // 更新页面标题
  const title = to.meta.title as string
  if (title) {
    document.title = title
  }

  next()
})

/**
 * 路由后置钩子
 */
router.afterEach(() => {
  // 可以在这里处理路由切换后的逻辑
})

export default router
