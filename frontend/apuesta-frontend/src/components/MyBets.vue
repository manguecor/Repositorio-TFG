<template>
  <div class="container">
    <br>
    <span style="margin-right: 4px">MIS PUNTOS: <button class="puntos" disabled> {{this.mypoints}} </button></span>
    <div class="container1">
    <br><br><span class="titulos"> APUESTAS DISPONIBLES </span><br><br>
    <table class="table table-striped">
        <thead>
            <th style="width:35%"> DESCRIPCION </th>
            <th style="width:25%"> ESTADO </th>
            <th style="width:20%"> TIPO </th>
            <th style="width:20%"> </th>
        </thead>
        <tbody>
            <tr v-for = "bet in bets" v-bind:key = "bet.id">
                <td v-if="bet.estado!='CERRADA'"> {{bet.description}} </td>
                <td v-if="bet.estado!='CERRADA'"> {{bet.estado}} </td>
                <td v-if="bet.estado!='CERRADA'"> {{bet.betType}}</td>
                <td v-if="bet.estado!='CERRADA'"><router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link></td>
            </tr>
        </tbody>
    </table>
    </div>
    <div class="container2">
    <br><br><span class="titulos">CLASIFICACION </span><br><br>
    <table class="table table-striped">
      <thead>
          <th style="width:28%"> POSICION </th>
          <th style="width:36%"> JUGADOR </th>
          <th style="width:36%"> PUNTOS </th>
      </thead>
      <tbody>
            <tr v-for= "(player,index) in players" v-bind:key= "player.id">
              <td> {{this.positions[index]}} </td>
              <td> {{player.client.user.username}} </td>
              <td> {{player.points}} </td>
            </tr> 
      </tbody>
    </table>
    </div>
    
</div><br><br><br><br><br><br><br><br><br><br><br>
<div class="botones">
<router-link :to="'/leagues/' + this.$route.params.leagueId + '/invitation'" class="btn btn-success">Invitar a un amigo</router-link>
<a style="margin-left: 14px" :href="'/leagues/' + this.$route.params.leagueId + '/bets/record'" class="btn btn-success">Historial de apuestas</a>
<a style="margin-left: 14px" href="/leagues/myLeagues" class="btn btn-success">Volver</a>
</div>
</template>

<script>
import BetService from "../services/BetService";
import PlayerService from "../services/PlayerService";

export default {
  name: "MyBets",
  data(){
    return {
      bets: [],
      players: [],
      mypoints: null,
      positions: []
    }
  },
  methods: {
    
    getBetsByLeague() {
        BetService.getBetsByLeague(this.$route.params.leagueId).then((response) => {
            this.bets = response.data;
        })
    },

    getPlayersByLeague() {
      PlayerService.getPlayersByLeague(this.$route.params.leagueId).then((response) => {
        this.players = response.data;
        for(let value in this.players){
          this.positions[value] = parseInt(value)+1;
          if(this.players[value].client.user.username==localStorage.getItem('username')){
            this.mypoints = this.players[value].points;
          }
        }
        console.log(this.mypoints);
        console.log(this.players);
        console.log(this.positions);
      })
    }
  },
  created() {
        this.getBetsByLeague()
        this.getPlayersByLeague()
    }
}
</script>

<style>

.puntos {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 100px; 
   height: 35px; 
   border: 1px solid #555;
   background-color: #3bd03d;
   color: white;
   border-radius: 6px;
   vertical-align: middle;
}

.botones {
  margin-left: 550px;
  position: fixed;
  padding-bottom: 30px;
}


</style>