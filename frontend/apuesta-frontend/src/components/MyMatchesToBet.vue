<template>
  <div class="container">
    <br><h2 class="text-center"> Detalles de la apuesta </h2><br>
    <div v-if="this.bets.betType=='WINNER'">
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
    </div>
    <div v-if="this.bets.betType=='RESULT'">
      <table class="table table-striped">
        <thead>
            <th> </th>
            <th> </th>
            <th> </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <td> 
                    <a type="checkbox" v-if="match.status=='SCHEDULED'">{{match.homeTeam}}</a>
                    <input type="number" id="goalsHomeTeam" name="goalsHomeTeam" min="0" max="15" v-if="match.status=='SCHEDULED'">
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.homeTeam}}</a>
                </td>
                <td> 
                    <a type="checkbox">-</a>
                </td>
                <td> 
                    <input type="number" id="goalsAwayTeam" name="goalsAwayTeam" min="0" max="15" v-if="match.status=='SCHEDULED'">
                    <a type="checkbox" v-if="match.status=='SCHEDULED'">{{match.awayTeam}}</a>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.awayTeam}}</a>
                </td>
                <td>
                    <button type="checkbox" v-if="match.status=='SCHEDULED'" @click="saveResult()">Guardar resultado</button>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
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
      bets: [],
      result: []
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
          console.log(this.bets);
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

    saveResult(){
      let result = this.result;
      result[0]=this.$route.params.betId;
          let homeResult = document.getElementById("goalsHomeTeam").value;
          let awayResult = document.getElementById("goalsAwayTeam").value;
          result[result.length]=homeResult;
          result[result.length]=awayResult;
          //Falta que pille todos los resultados, ademas hay que mirar porque el ultimo partido no lo mete
          console.log(result);
    },

    savePlayerBet(){
      if(this.bets.betType=='WINNER'){
        PlayerBetService.postPlayerBet(this.matchBet).then((response) => {
            console.log(response);
      })
      } else if(this.bets.betType=='RESULT'){
        PlayerBetService.postPlayerBetResult(this.result).then((response) => {
          console.log(response);
        })
      }
      
    },

    checkPlayerBet(){
      if(this.bets.betType=='WINNER'){
        PlayerBetService.checkPlayerBet(this.$route.params.betId).then((response) => {
          console.log(response);
      })
      } else if(this.bets.betType=='RESULT'){
        PlayerBetService.checkPlayerBetResult(this.$route.params.betId).then((response) =>{
          console.log(response);
        })
      }
    }
  },
  created() {
        this.getMatches()
        this.getBetByBetId()
    }
}
</script>