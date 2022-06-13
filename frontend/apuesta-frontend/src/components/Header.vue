<template>
  <header id="header">
      <ul class="nav">
          <img class="logo" alt="SocialBet logo" src="../assets/logoHeader.jpg" width="60" height="35">
          <li><a href="/">Inicio</a></li>
          <li><a href="/competitions">Competiciones</a></li>
          <li><a v-if="currentUser!='admin'" href="/leagues/myLeagues">Mis ligas</a></li>
          <li><a v-if="isAuthenticated && currentUser=='admin'" href="/leagues">Todas las ligas</a></li>
          <li><a href="/matches/today">Partidos</a></li>
        <ul class="nav1">
          <li><a v-if="!isAuthenticated" href="/login">Iniciar sesión</a></li>
        </ul>
        <ul class="nav2">
          <li><a v-if="!isAuthenticated" href="/register">Registrarse</a></li>
        </ul>
        <ul class="nav3">
          <li><a v-if="isAuthenticated" href="/">{{ currentUser }}</a>
            <ul>
                <li><a v-if="isAuthenticated" href="/clients/profile">Mi perfil</a></li>
                <li><a v-if="isAuthenticated" href="/invitation/myInvitations">Mis invitaciones</a></li>
                <li><a v-if="isAuthenticated" href="/logout">Cerrar sesión</a></li>
            </ul>
          </li>
        </ul>
      </ul>
  </header>
  <router-view/>
</template>

<script>
import LoginService from '../services/LoginService';

export default {
    name: 'MyHeader',
    computed: {
        currentUser(){
            return localStorage.getItem('username')
        }
    },
    data() {
        return {
            isAuthenticated: LoginService.isUserLoggedIn()
        };
    }
}

</script>


<style>

#header {
    margin:auto;
    overflow: hidden;
    width: 100%;
    height: auto;
    font-family: Arial, Helvetica, sans-serif;
}

ul, ol {
    list-style: none;
}

.nav{
    background-color:#3bd03d;
    overflow: hidden;
    margin-top: 0px;
    block-size: 70px;
}

.nav li a {
    background-color:#3bd03d;
    color:black;
    text-align: center;
    text-decoration: none;
    padding: 22px 15px;
    display: block;
    font-weight: bold;
}

.nav li a:hover {
    background-color: black;
    color: white;
}

.nav > li {
    float: left;
}

.nav li ul {
    display:none;
    position: absolute;
    min-width: 100px;
}

.nav li:hover > ul {
    display: block;
}

.nav1 {
    margin-left: 670px;
}

.nav2 {
    margin-left: 0px;
}

.nav3 {
    margin-left: 60px;
}

.logo{
    margin-top: 18px;
    margin-left: 10px;
}
</style>