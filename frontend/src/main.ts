import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import './style.css'
import App from './App.vue'

const app = createApp(App)

// 使用Pinia进行状态管理
app.use(createPinia())

// 使用Vue Router进行路由管理
app.use(router)

app.mount('#app')
