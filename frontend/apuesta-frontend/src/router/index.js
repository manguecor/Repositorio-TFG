import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MyCompetitions from '@/components/MyCompetitions.vue'
import Register from '@/components/Register.vue'
import LeaguesC from '@/components/LeaguesC.vue'
import MyLeagues from '@/components/MyLeagues.vue'
import MyLeague from '@/components/MyLeague.vue'
import MyCompetitionDetails from '@/components/MyCompetitionDetails.vue'
import MyNextMatches from '@/components/MyNextMatches.vue'
import MyMatchesToday from '@/components/MyMatchesToday.vue'
import MyLastMatches from '@/components/MyLastMatches.vue'
import Login from '@/components/MyLogin.vue'
import Logout from '@/components/MyLogout.vue'
import LoginService from '@/services/LoginService'
import MyMatchesToBet from '@/components/MyMatchesToBet.vue'
import MyBets from '@/components/MyBets.vue'

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
  },
  {
    path: '/matches/:betId',
    name: 'MyMatches',
    component: MyMatchesToBet
  },
  {
    path: '/bets',
    name: 'MyBets',
    component: MyBets
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
