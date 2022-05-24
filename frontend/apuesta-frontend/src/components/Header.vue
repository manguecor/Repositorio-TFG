<template>
  <!--<div class="nav">
    <router-link to="/">Inicio</router-link>
    <router-link to="/competitions">Competiciones</router-link>
    <router-link to="/leagues/myLeagues">Mis Ligas</router-link>
    <router-link to="/matches/today">Partidos</router-link>
    <div class="nav2">
        <router-link v-if="!isAuthenticated" to="/login">Iniciar sesión</router-link>
        <router-link v-if="!isAuthenticated" to="/register">Registrarse</router-link>
        <router-link v-if="isAuthenticated" to="/clients/profile" ><i class="fa-solid fa-user"></i>{{ currentUser }}</router-link>
        <router-link v-if="isAuthenticated" to="/logout">Cerrar sesión</router-link>
    </div>
  </div>
  <router-view/>-->

  <header id="header">
      <ul class="nav">
          <li><a href="/">Inicio</a></li>
          <li><a href="/competitions">Competiciones</a></li>
          <li><a href="/leagues/myLeagues">Mis ligas</a></li>
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


<!--<style>
.nav{
    background-color:#3bd03d;
    overflow: hidden;
    margin-top: 0px;
    block-size: 70px;
}
.nav a {
    float:left;
    color:#010102;
    padding: 22px 16px;
    text-align: center;
    font-size: 18px;
    text-decoration: none;
    margin-right: 10px;
}
.nav a:hover {
    background-color: #030303;
    color:#fcfcfc;
}
.nav2 {
    margin-left: 730px;
}
</style>-->

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

</style>