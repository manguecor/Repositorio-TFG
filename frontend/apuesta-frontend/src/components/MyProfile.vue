<template>
<form @submit="editClient">
   <div class="row2">
            <div class="col-12">
                <br>
                <span class="titulos"> MI PERFIL</span><br><br>
                <img alt="SocialBet logo" src="../assets/logoNuevo.jpg" height="150" width="250">
                <br><br>
                <div class="form-group">
                    <label for="price">Contraseña:</label>
                    <input id="password" v-model="client.user.password" placeholder="Password" type="password" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input id="name" v-model="client.name" placeholder="Name" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="surname">Apellidos:</label>
                    <input id="surname" v-model="client.surname" placeholder="Surname" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input id="email" v-model="client.email" placeholder="Email" type="email" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <p>
                        <input type="submit" value="Editar cliente" class="btn btn-success">
                    </p>
                    <a href="/" class="btn btn-success">Volver</a>
                </div>
            </div>
        </div>
</form>
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
    editClient: function(e) {
        e.preventDefault();
        ClientService.editClient(this.client).then((response) => {
            console.log(response);
            this.client = response.data;
            window.location.href = "/";
        })
    }
  },
  created() {
        this.getClient()
    }
}
</script>