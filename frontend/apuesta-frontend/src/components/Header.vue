<template>
  <div class="nav">
    <router-link to="/">Inicio</router-link>
    <router-link to="/competitions">Competiciones</router-link>
    <router-link to="/">Partidos</router-link>
    <router-link to="/leagues">Mis Ligas</router-link>
    <div class="nav2">
    <router-link to="/">Iniciar sesión</router-link>
    <router-link to="/register">Registrarse</router-link>
    </div>
  </div>
  <router-view/>
</template>

<script>
export default {
    name: 'MyHeader'
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