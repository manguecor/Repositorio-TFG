<template>
   <div class="row2">
            <div class="col-12">
                <h2>Registrarse</h2>
                <br>
                <div class="form-group">
                    <label for="price">Contraseña:</label>
                    <input id="password" v-model="client.user.password" placeholder="Password" type="password" class="form">
                </div>
                <br>
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input id="name" v-model="client.name" placeholder="Name" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <label for="surname">Apellidos:</label>
                    <input id="surname" v-model="client.surname" placeholder="Surname" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input id="email" v-model="client.email" placeholder="Email" type="email" class="form">
                </div>
                <br>
                <div class="form-group">
                    <label for="fav_team">Equipo favorito:</label>
                    <input id="fav_team" v-model="client.fav_team" placeholder="Fav_team" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a href="/" @click="editClient" class="btn btn-success">Editar cliente</a>
                    <br>
                    <br>
                    <a href="/" class="btn btn-info">Volver</a>
                </div>
            </div>
        </div>
</template>

<script>
import ClientService from '../services/ClientService';  

export default {
  name: "MyProfile",
  data(){
    return {
      client: {
          user: {
              username: null,
              password: null
          }
      }
    }
  },
  methods: {

    getClient(){
        ClientService.getClient().then((response) => {
          this.client = response.data;
        })
    },
    editClient() {
        ClientService.editClient(this.client).then((response) => {
            console.log(response);
            this.client = response.data;
        })
    }
  },
  created() {
        this.getClient()
    }
}
</script>