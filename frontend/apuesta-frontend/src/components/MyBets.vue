<template>
  <div class="container">
    <br><h2 class="text-center"> Apuestas disponibles </h2><br>
    <table class="table table-striped">
        <thead>
            <th> Id </th>
            <th> Estado de la apuesta </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "bet in bets" v-bind:key = "bet.id">
                <td> {{bet.id}} </td>
                <td> {{bet.estado}} </td>
                <td> <router-link :to="'/matches/' + bet.id" class="btn btn-success">Ver apuesta</router-link></td>
            </tr>
        </tbody>
    </table>
   
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
    
    getBets() {
        BetService.getBets().then((response) => {
            this.bets = response.data;
        })
    }
  },
  created() {
        this.getBets()
    }
}
</script>