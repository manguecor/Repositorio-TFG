<template>
  <div class="container">
    <br><h2 class="text-center"> Apuestas disponibles </h2><br>
    <table class="table table-striped">
        <thead>
            <th> Descripcion </th>
            <th> Estado de la apuesta </th>
            <th> Tipo de apuesta </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "bet in bets" v-bind:key = "bet.id">
                <td> {{bet.description}} </td>
                <td> {{bet.estado}} </td>
                <td> {{bet.betType}}</td>
                <!--<td>
                  <div v-if="bet.betType=='WINNER'">
                    <router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link>
                  </div>  
                  <div v-else-if="bet.betType=='RESULT'">
                    <router-link :to="'/matches/result/' + bet.id" class="btn btn-success">Ver apuesta</router-link>
                  </div>
                </td>-->
                <td><router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link></td>
            </tr>
        </tbody>
    </table>
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