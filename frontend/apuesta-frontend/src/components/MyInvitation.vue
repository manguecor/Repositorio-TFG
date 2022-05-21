<template>
   <div class="row3">
            <div class="col-12">
                <h2>Invitar a un amigo</h2>
                <br>
                <div class="form-group">
                    <label for="username">Usuario del amigo:</label>
                    <input id="username" v-model="username" placeholder="Usuario" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a class="btn btn-success" @click="checkUsernameExists()">Comprobar usuario</a>
                    <a v-if="this.exists==true" @click="sendInvitation()" class="btn btn-success">Enviar invitación</a>
                    <br>
                    <br>
                </div>
            </div>
            <a href="/leagues/myLeagues" class="btn btn-success">Volver</a>
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
            LoginService.existUsername(this.username).then((response) => {
                this.exists = response.data;
                console.log(this.exists);
                console.log(this.username);
                console.log(this.currentUser);
            })
        },

        sendInvitation(){
            let invitation = this.invitation;
            invitation[0] = this.username;
            invitation[1] = this.$route.params.leagueId;
            InvitationService.postInvitation(this.invitation).then((response) => {
                console.log(response);
            })
        }
    }  
}
</script>