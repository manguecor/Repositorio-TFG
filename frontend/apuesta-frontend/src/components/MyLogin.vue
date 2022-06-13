<template>
<form @submit="login">
  <section>
    <br><br>
    <img alt="SocialBet logo" src="../assets/logoNuevo.jpg" height="150" width="250">
    <div class="container">
      <br><br>
      <p v-if="errors.length">
        <b>Por favor, corriga el(los) siguiente(s) error(es): </b>
          <ul>
            <li v-for="error in errors" v-bind:key="error.id">{{error}}</li>
          </ul>
        </p>
      <div class="form-group">
        <label for="username">Usuario:</label>
        <input id="username" v-model="username" placeholder="Usuario" type="text" class="form" required>
      </div>
      <br>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input id="password" v-model="password" placeholder="Contraseña" type="password" class="form" required>
      </div>
      <br>
      <p>
        <input type="submit" value="Iniciar sesion" class="btn btn-success">
      </p>
    </div>
  </section>
</form>
</template>
<script>

import LoginService from "../services/LoginService";
import axios from "axios";
import ClientService from "../services/ClientService";

export default {
  name: "MyLogin",
  data() {
    return {
      username: "",
      password: "",
      errors: []
      
    };
  },
  methods: {
    
    login: function(e) {

      this.errors = []

      let comp = false;
      for(let value in this.clients) {
        comp = (this.clients[value].user.username == this.username && this.clients[value].user.password == this.password);
        if(comp) {
          break;
        }
      }
      if(!comp) {
          this.errors.push('El usuario no existe');
      }
      

      e.preventDefault();

      let user = { 
                    "username": this.username,
                    "password": this.password
      }
      if(this.errors.length==0) {
          LoginService.postLogin(user).then( (response) => {
          let token = response.data.token;
          let username = response.data.username;
          localStorage.setItem("token", 'Bearer ' + token);
          localStorage.setItem("username", username);
          axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

          LoginService.getUsername(localStorage.getItem("username"));
          })
          this.$router.push("/loginSuccess");
      } 
      
    },

    getClients() {
      ClientService.getClients().then(data => {
        this.clients = data.data;
      })
    }
  },

  created() {
    this.getClients()
  }
};
</script>