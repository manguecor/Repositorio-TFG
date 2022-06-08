<template>
  <div class="container">
    <br>
    <span class="titulos"> MIS INVITACIONES</span><br><br>
    <table class="table table-striped">
        <thead>
            <th> LIGA </th>
            <th> INVITADO POR</th>
            <th> ESTADO</th>
            <th> </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "invitation in invitations" v-bind:key = "invitation.id">
                <td> {{invitation.league.name}}</td>
                <td> {{invitation.invitingClient}} </td>
                <td> {{invitation.invitationState}}</td>
                <td><a href="/invitation/myInvitations" v-if="invitation.invitationState=='PENDIENTE'" @click="acceptInvitation(invitation.id)" class="btn btn-success">Aceptar invitacion</a></td>
                <td><a href="/invitation/myInvitations" v-if="invitation.invitationState=='PENDIENTE'" @click="declineInvitation(invitation.id)" class="btn btn-success">Rechazar invitacion</a></td>
                <td><a href="/invitation/myInvitations" v-if="invitation.invitationState!='PENDIENTE'" @click="deleteInvitation(invitation.id)" class="btn btn-success">Borrar invitacion</a></td> 
            </tr>
        </tbody>
    </table>
    <a href="/leagues/myLeagues" class="btn btn-success">Volver</a>
</div>
</template>

<script>
import InvitationService from '../services/InvitationService';

export default {
  name: "MyInvitations",
  data(){
    return {
      invitations:[]
    }
  },
  methods: {
    
    getInvitations() {
        InvitationService.getInvitations().then((response) => {
            this.invitations = response.data;
        })
    },

    acceptInvitation(invitationId) {
        InvitationService.acceptInvitation(invitationId).then((response) => {
          console.log(response);
        })
    },

    declineInvitation(invitationId) {
        InvitationService.declineInvitation(invitationId).then((response) => {
          console.log(response);
        })
    },

    deleteInvitation(invitationId) {
        InvitationService.deleteInvitation(invitationId).then((response) => {
          console.log(response);
        })
    }
  },
  created() {
        this.getInvitations()
    }
}
</script>
