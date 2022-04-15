import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import MyTeamsList from '@/components/MyTeamsList.vue'
import MyMatchesList from '@/components/MyMatchesList.vue'

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
    path: '/teams/:competitionId',
    name: 'teams',
    component: MyTeamsList
  },
  {
    path: '/teams/:teamId/nextMatches',
    name: 'matches',
    component: MyMatchesList
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
