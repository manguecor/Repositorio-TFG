<template>
  <div class="container">
    <br><h2 class="text-center"> Detalles de la apuesta </h2><br>
    <table class="table table-striped">
        <thead>
            <th> </th>
            <th> </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <td> 
                    <button type="checkbox" v-if="match.status=='SCHEDULED'" @click="saveHomeTeam(match.api_id)">{{match.homeTeam}}</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.homeTeam}}</a>
                </td>
                <td> 
                    <button type="checkbox" v-if="match.status=='SCHEDULED'" @click="saveDraw(match.api_id)">-</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">-</a>
                </td>
                <td> 
                    <button type="checkbox" v-if="match.status=='SCHEDULED'" @click="saveAwayTeam(match.api_id)">{{match.awayTeam}}</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.awayTeam}}</a>
                </td>
            </tr>
        </tbody>
    </table>
    <a href="/" class="btn btn-success" @click="savePlayerBet()">Realizar apuesta</a>
    <a href="/" class="btn btn-success" v-if="this.bets.estado=='PENDIENTE'" @click="checkPlayerBet()">Comprobar apuesta</a>
</div>
</template>

<script>
import MatchService from "../services/MatchService";
import PlayerBetService from '../services/PlayerBetService';
import BetService from "../services/BetService";

export default {
  name: "MyMatches",
  data(){
    return {
      matches: [],
      matchBet: [],
      bets: []
    }
  },
  methods: {
    
    getMatches() {
        MatchService.getMatchesByBetId(this.$route.params.betId).then((response) => {
            this.matches = response.data;
            console.log(this.matches);
        })
    },

    getBetByBetId(){
        BetService.getBetByBetId(this.$route.params.betId).then((response) => {
          this.bets = response.data;
          console.log(this.bets.estado);
        })
    },

    saveHomeTeam(matchAPIId){
      let matchBet = this.matchBet;
      matchBet[0]=this.$route.params.betId;
      if(!matchBet.includes(matchAPIId)){
          matchBet[matchBet.length]=matchAPIId;
          matchBet[matchBet.length]="HOME_TEAM";
          console.log(matchBet);
      }
    },
    
    saveAwayTeam(matchAPIId){
      let matchBet = this.matchBet;
      matchBet[0]=this.$route.params.betId;
      if(!matchBet.includes(matchAPIId)){    
          matchBet[matchBet.length]=matchAPIId;
          matchBet[matchBet.length]="AWAY_TEAM";
          console.log(matchBet);
      }
    },

    saveDraw(matchAPIId){
      let matchBet = this.matchBet;
      matchBet[0]=this.$route.params.betId;
      if(!matchBet.includes(matchAPIId)){
          matchBet[matchBet.length]=matchAPIId;
          matchBet[matchBet.length]="DRAW";
          console.log(matchBet);
      }
    },

    savePlayerBet(){
      PlayerBetService.postPlayerBet(this.matchBet).then((response) => {
            console.log(response);
      })
    },

    checkPlayerBet(){
      PlayerBetService.checkPlayerBet(this.$route.params.betId).then((response) => {
            console.log(response);
      })
    }
  },
  created() {
        this.getMatches()
        this.getBetByBetId()
    }
}
</script>