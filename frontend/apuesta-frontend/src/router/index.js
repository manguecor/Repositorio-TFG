import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import LeaguesC from '@/components/LeaguesC.vue'
import MyLeagues from '@/components/MyLeagues.vue'
import MyLeague from '@/components/MyLeague.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/competitions',
    name: 'competitions',
    component: MyCompetitions
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/leagues/new',
    name: 'leagueNew',
    component: LeaguesC
  },
  {
    path: '/leagues',
    name: 'leagues',
    component: MyLeagues
  },
  {
    path: '/leagues/:leagueId',
    name: 'league',
    component: MyLeague
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
