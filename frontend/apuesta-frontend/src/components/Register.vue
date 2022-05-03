<template>
   <div class="row2">
            <div class="col-12">
                <h2>Registrarse</h2>
                <br>
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input id="username" v-model="client.user.username" placeholder="Username" type="text" class="form">
                </div>
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
                    <a href="/login" @click="register" class="btn btn-success">Registrarse</a>
                    <br>
                    <br>
                    <a href="/" class="btn btn-info">Volver</a>
                </div>
            </div>
        </div>
</template>

<script>

import RegisterService from '../services/RegisterService'; 

export default {
    name: 'MyRegister',
    data() {
        return {
            
            client: {
                name: null,
                surname: null,
                email: null,
                fav_team: null,
                user: {
                    username: null,
                    password: null
                }
            }
        };
    },
    methods: {
        register(){
            RegisterService.postRegisterClient(this.client).then(data => {
                console.log(data);
            });
        }
    }  
}
</script>