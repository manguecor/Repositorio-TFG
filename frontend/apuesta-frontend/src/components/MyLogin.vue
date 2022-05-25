<template>
<form @submit="login">
  <section>
    <br>
    <h2>Iniciar sesión</h2>
    <div class="container">
      <br>
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
      <!--<button class="btn btn-success" type="submit">Iniciar sesión</button>-->
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

      let error = false;
      for(let value in this.clients) {
        error = this.clients[value].user.username != this.username || this.clients[value].user.password != this.password;
        console.log(error);
      }
      if(error) {
          this.errors.push('El usuario no existe');
      }
      

      e.preventDefault();

      let user = { 
                    "username": this.username,
                    "password": this.password
      }
      if(this.errors.length==0) {
          LoginService.postLogin(user).then( (response) => {
          console.log(response);
          let token = response.data.token;
          let username = response.data.username;
          localStorage.setItem("token", 'Bearer ' + token);
          localStorage.setItem("username", username);
          axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

          LoginService.getUsername(localStorage.getItem("username"));
          })
          window.location.href = "/loginSuccess";
          //this.$router.push("/loginSuccess");
      } 
      
    },

    getClients() {
      ClientService.getClients().then(data => {
        this.clients = data.data;
        console.log(this.clients);
      })
    }
  },

  created() {
    this.getClients()
  }
};
</script>