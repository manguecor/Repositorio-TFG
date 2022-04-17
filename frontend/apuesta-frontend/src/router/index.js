import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/MyLogin.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/competitions',
    name: 'MyCompetitions',
    component: MyCompetitions
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/login',
    name: 'MyLogin',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
