<template>
<form @submit="register">
   <div class="row2">
            <div class="col-12">
                <h2>Registrarse</h2>
                <br>
                <p v-if="errors.length">
                    <b>Por favor, corriga el(los) siguiente(s) error(es): </b>
                    <ul>
                        <li v-for="error in errors" v-bind:key="error.id">{{error}}</li>
                    </ul>
                </p>
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input id="username" v-model="client.user.username" placeholder="Usuario" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="price">Contraseña:</label>
                    <input id="password" v-model="client.user.password" placeholder="Contraseña" type="password" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input id="name" v-model="client.name" placeholder="Nombre" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="surname">Apellidos:</label>
                    <input id="surname" v-model="client.surname" placeholder="Apellidos" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input id="email" v-model="client.email" placeholder="Email" type="email" class="form" required>
                </div>
                <br>
                <div class="form-group">
                   <p>
                        <input type="submit" value="Registrarse" class="btn btn-success">
                    </p>
                   <!--<a href="/register" @click="checkForm" class="btn btn-success">Registrarse</a>-->
                   <a href="/" class="btn btn-info">Volver</a>
                </div>
            </div>
   </div>
</form>
</template>

<script>

import RegisterService from '../services/RegisterService'; 
import ClientService from '../services/ClientService';

export default {
    name: 'MyRegister',
    data() {
        return {
            
            client: {
                name: null,
                surname: null,
                email: null,
                user: {
                    username: null,
                    password: null
                }
            },
            usernames: [],
            errors: []
        };
    },
    methods: {

        register: function(e) {

            this.errors = [];

            console.log(this.usernames);

            for(let value in this.usernames) {
                if(this.usernames[value]==this.client.user.username) {
                    this.errors.push('El usuario ya existe');
                }
            }

            e.preventDefault();

            console.log(this.errors.length);
            if(this.errors.length==0) {
                RegisterService.postRegisterClient(this.client).then(data => {
                console.log(data);
                window.location.href = "/login";
            });

            }   
        },

        getUsernames() {
            ClientService.getClients().then(data => {
                let clients = data.data;
                for(let value in clients) {
                    this.usernames[value] = clients[value].user.username;
                }
                
            });
        }
    },

    created() {
        this.getUsernames()
    }



}
</script>