import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import Login from '@/components/MyLogin.vue'
import Logout from '@/components/MyLogout.vue'
import LoginService from '@/services/LoginService'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/competitions',
    name: 'MyCompetitions',
    component: MyCompetitions,
    beforeEnter: (to, from, next) => {
      if (LoginService.isUserLoggedIn()) {
          next()
      } else {
          next({ path: '/login'})
      }
    }
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
  },
  {
    path: "/logout",
    name: "MyLogout",
    component: Logout,
    beforeEnter: (to, from, next) => {
        if (LoginService.isUserLoggedIn()) {
            LoginService.logout();
        }
        next();
    },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
