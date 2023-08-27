import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '@/views/login/login.vue'

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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
