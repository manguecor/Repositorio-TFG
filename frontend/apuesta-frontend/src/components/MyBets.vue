<template>
  <div class="container">
    <br><span class="titulos"> APUESTAS DISPONIBLES </span><br><br>
    <table class="table table-striped">
        <thead>
            <th> DESCRIPCION </th>
            <th> ESTADO DE LA APUESTA </th>
            <th> TIPO DE APUESTA </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "bet in bets" v-bind:key = "bet.id">
                <td> {{bet.description}} </td>
                <td> {{bet.estado}} </td>
                <td> {{bet.betType}}</td>
                <td><router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link></td>
            </tr>
        </tbody>
    </table>
    <router-link :to="'/leagues/' + this.$route.params.leagueId + '/invitation'" class="btn btn-success">Invitar a un amigo</router-link>
    <a href="/leagues/myLeagues" class="btn btn-success">Volver</a>
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