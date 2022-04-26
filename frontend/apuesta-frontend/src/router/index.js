import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import MyCompetitionDetails from '@/components/MyCompetitionDetails.vue'
import MyNextMatches from '@/components/MyNextMatches.vue'
import MyMatchesToday from '@/components/MyMatchesToday.vue'
import MyLastMatches from '@/components/MyLastMatches.vue'

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
    name: 'MyCompetitionDetails',
    component: MyCompetitionDetails
  },
  {
    path: '/teams/:teamId/nextMatches',
    name: 'MyNextMatches',
    component: MyNextMatches
  },
  {
    path: '/matches/today',
    name: 'MyMatchesToday',
    component: MyMatchesToday
  },
  {
    path: '/matches/:teamId/results',
    name: 'MyLastMatches',
    component: MyLastMatches
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
