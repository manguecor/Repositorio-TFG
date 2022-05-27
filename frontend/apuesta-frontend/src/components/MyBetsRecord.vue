<template>
    <div class="container">
        <br><br><span class="titulos"> HISTORIAL DE APUESTAS </span><br><br>
    <table class="table table-striped">
        <thead>
            <th style="width:35%"> DESCRIPCION </th>
            <th style="width:25%"> ESTADO </th>
            <th style="width:20%"> TIPO </th>
            <th style="width:20%"> </th>
        </thead>
        <tbody>
            <tr v-for = "bet in bets" v-bind:key = "bet.id">
                <td v-if="bet.estado=='CERRADA'"> {{bet.description}} </td>
                <td v-if="bet.estado=='CERRADA'"> {{bet.estado}} </td>
                <td v-if="bet.estado=='CERRADA'"> {{bet.betType}}</td>
                <td v-if="bet.estado=='CERRADA'"><router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link></td>
            </tr>
        </tbody>
    </table>
    <a style="margin-left: 14px" :href="'/leagues/' + this.$route.params.leagueId + '/bets/'" class="btn btn-success">Volver</a>
    </div>
</template>

<script>
import BetService from "../services/BetService";

export default {
  name: "MyBets",
  data(){
    return {
      bets: []
    }
  },
  methods: {
    
    getBetsByLeague() {
        BetService.getBetsByLeague(this.$route.params.leagueId).then((response) => {
            this.bets = response.data;
        })
    }
  },
  created() {
        this.getBetsByLeague()
    }
}
</script>