<template>
   <div class="row3">
            <div class="col-12">
                <br>
                <span class="titulos">INVITAR A UN AMIGO</span><br><br>
                <img src="../assets/logoNuevo.jpg" width="250" height="150">
                <br><br>
                <div class="form-group">
                    <label for="username">Usuario del amigo:</label>
                    <input id="username" v-model="username" placeholder="Usuario" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a class="btn btn-success" @click="checkUsernameExists()">Comprobar usuario</a>
                    <router-link :to="'/leagues/' + this.$route.params.leagueId + '/bets'" v-if="this.exists==true" @click="sendInvitation()" class="btn btn-success">Enviar invitación</router-link>
                    <br>
                    <br>
                </div>
            </div>
            <router-link :to="'/leagues/' + this.$route.params.leagueId + '/bets'" class="btn btn-success">Volver</router-link>
    </div>
</template>

<script>

import LoginService from '../services/LoginService';
import InvitationService from '../services/InvitationService';

export default {
    name: 'MyInvitation',
    data() {
        return {
           username: null,
           exists: null,
           invitation: [],
           currentUser: localStorage.getItem('username')
        };
    },
    methods: {
        checkUsernameExists(){
            if(this.username!=this.currentUser){
                LoginService.existUsername(this.username).then((response) => {
                this.exists = response.data;
            })
            } else {
                this.exists = false;
            }
        },

        sendInvitation(){
            let invitation = this.invitation;
            invitation[0] = this.username;
            invitation[1] = this.$route.params.leagueId;
            InvitationService.postInvitation(this.invitation);
        }
    }  
}
</script>