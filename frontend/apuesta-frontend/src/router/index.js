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
import Login from '@/components/MyLogin.vue'
import Logout from '@/components/MyLogout.vue'
import LoginService from '@/services/LoginService'
import MyMatchesToBet from '@/components/MyMatchesToBet.vue'
import MyBets from '@/components/MyBets.vue'
import MyLoginSuccess from '@/components/MyLoginSuccess.vue'
import LeagueJoin from '@/components/LeagueJoin.vue'
import MyProfile from '@/components/MyProfile.vue'
import MyInvitation from '@/components/MyInvitation.vue'
import MyInvitations from '@/components/MyInvitations.vue'
import MyBetsRecord from '@/components/MyBetsRecord.vue'
import AllLeagues from '@/components/AllLeagues.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/clients/profile',
    name: 'profile',
    component: MyProfile
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
    path: '/leagues/myLeagues',
    name: 'MyLeagues',
    component: MyLeagues,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues',
    name: 'leagues',
    component: AllLeagues,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues/:leagueId',
    name: 'league',
    component: MyLeague,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues/join',
    name: 'leagueJoin',
    component: LeagueJoin,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
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
    path: '/login',
    name: 'MyLogin',
    component: Login
  },
  {
    path: '/loginSuccess',
    name: 'MyLoginSuccess',
    component: MyLoginSuccess,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
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
    component: MyMatchesToBet,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues/:leagueId/bets',
    name: 'MyBets',
    component: MyBets,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues/:leagueId/bets/record',
    name: 'MyBetsRecord',
    component: MyBetsRecord,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/leagues/:leagueId/invitation',
    name: 'MyInvitation',
    component: MyInvitation,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
      }
      next();
    },
  },
  {
    path: '/invitation/myInvitations',
    name: 'MyInvitations',
    component: MyInvitations,
    beforeEnter: (to, from, next) => {
      if (!LoginService.isUserLoggedIn()) {
        next('/login');
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
