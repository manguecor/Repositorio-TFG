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
                    <input id="username" v-model="client.user.username" placeholder="Username" type="text" class="form" required>
                </div>
                <br>
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
                    <label for="fav_team">Equipo favorito:</label>
                    <input id="fav_team" v-model="client.fav_team" placeholder="Fav_team" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                   <p>
                        <input type="submit" value="Enviar" class="btn btn-success">
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
            RegisterService.getClients().then(data => {
                this.usernames = data.data;
                
            });
        }
    },

    created() {
        this.getUsernames()
    }



}
</script>