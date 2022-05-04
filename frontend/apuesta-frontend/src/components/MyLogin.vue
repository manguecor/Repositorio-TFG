<template>
  <section>
    <br>
    <h2>Iniciar sesión</h2>
    <div class="container">
      <br>
      <div class="form-group">
        <label for="username">Usuario:</label>
        <input id="username" v-model="username" placeholder="Usuario" type="text" class="form">
      </div>
      <br>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input id="password" v-model="password" placeholder="Contraseña" type="text" class="form">
      </div>
      <br>
      <a href="/" class="btn btn-success" v-on:click="login(); getUsername();">Iniciar sesión</a>
    </div>
  </section>
</template>
<script>

import LoginService from "../services/LoginService";
import axios from "axios";

export default {
  name: "MyLogin",
  data() {
    return {
      username: "",
      password: ""
      
    };
  },
  methods: {
    
    login() {
      let user = { 
                    "username": this.username,
                    "password": this.password
      } 
      LoginService.postLogin(user).then( (response) => {
            console.log(response);
            let token = response.data.token;
            let username = response.data.username;
            localStorage.setItem("token", 'Bearer ' + token);
            localStorage.setItem("username", username);
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
            })
    },
    getUsername() {
            LoginService.getUsername(localStorage.getItem("username")).then((response) => {
            console.log(response);
            })
        }
  }
};
</script>