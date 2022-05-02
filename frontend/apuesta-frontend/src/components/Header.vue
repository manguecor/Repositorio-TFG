<template>
  <div class="nav">
    <router-link to="/">Inicio</router-link>
    <router-link to="/competitions">Competiciones</router-link>
    <router-link to="/leagues">Mis Ligas</router-link>
    <router-link to="/matches/today">Partidos</router-link>
    <div class="nav2">
        <router-link v-if="!isAuthenticated" to="/login">Iniciar sesión</router-link>
        <router-link v-if="!isAuthenticated" to="/register">Registrarse</router-link>
        <router-link v-if="isAuthenticated" to="/" ><i class="fa-solid fa-user"></i>{{ currentUser }}</router-link>
        <router-link v-if="isAuthenticated" to="/logout">Cerrar sesión</router-link>
    </div>
  </div>
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
</style>