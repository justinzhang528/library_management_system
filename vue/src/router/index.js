import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '@/views/login/login.vue'
import Cookies from 'js-cookie'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/',
    name: 'layout',
    redirect: '/home',
    component: () => import('@/views/layout.vue'),
    children: [
      {
        path: '/home',
        name: 'home',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/home/HomeView.vue')
      },
      {
        path: '/addUser',
        name: 'addUser',
        component: () => import('../views/user/AddUser.vue')
      },
      {
        path: '/userList',
        name: 'userList',
        component: () => import('../views/user/UserList.vue')
      },
      {
        path: '/addAdmin',
        name: 'addAdmin',
        component: () => import('../views/admin/AddAdmin.vue')
      },
      {
        path: '/adminList',
        name: 'adminList',
        component: () => import('../views/admin/AdminList.vue')
      }      
    ]
  },
  {
    path: '*',
    component: () => import('@/views/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 解决重复点击路由报错的BUG
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err)
}

// 防止退出登錄後依然能夠進入到主頁面
router.beforeEach((to,from,next)=>{
  if(to.path === '/login') next()
  const admin = Cookies.get("admin")
  if(!admin && to.path !== '/login') return next("/login")  //強制退回登錄頁面

  // 訪問 /home的時候，並且cookie裡存在數據，則直接放行
  next()
})

export default router
