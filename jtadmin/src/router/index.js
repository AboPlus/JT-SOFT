// import 导入vue.js  需要提前依赖
import Vue from 'vue'
// import 导入router.js
import VueRouter from 'vue-router'
// 手动导入自己的组件
import Login from '../components/Login.vue'

// 在vue对象中声明路由对象 整个脚手架中都可以引用路由
Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login}
]

//创建路由对象
const router = new VueRouter({
  routes
})

export default router
