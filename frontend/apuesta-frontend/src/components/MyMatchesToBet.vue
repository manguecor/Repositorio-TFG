<template>
  <div class="container">
    <br><h2 class="text-center"> Detalles de la apuesta </h2><br>
    <div v-if="this.bets.betType=='WINNER'">
    <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL </th>
            <th> </th>
            <th> EQUIPO VISITANTE</th>
            <th> RESULTADO</th>
            <th> APUESTA</th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id" >
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
                <td>
                  <a type="checkbox" v-if="match.status!='SCHEDULED'">{{match.result}}</a>
                </td>
                <td>
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <span v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result" class="acierto">
                      {{playerBet.playerResult}}
                    </span>
                    <span v-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result" class="fallo">
                      {{playerBet.playerResult}}
                    </span>
                  </div>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
    <div v-if="this.bets.betType=='RESULT'">
      <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL </th>
            <th> </th>
            <th> EQUIPO VISITANTE</th>
            <th> RESULTADO</th>
            <th> APUESTA</th>
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
                  <a type="checkbox" v-if="match.status!='SCHEDULED'">{{match.result}}</a>
                </td>
                <td>
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <span v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result" class="acierto">
                      {{playerBet.playerResult}}
                    </span>
                    <span v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[0] == match.result[0]" class="soloGol">
                      {{playerBet.playerResult}}
                    </span>
                    <span v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[2] == match.result[2]" class="soloGol">
                      {{playerBet.playerResult}}
                    </span>
                    <span v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result" class="fallo">
                      {{playerBet.playerResult}}
                    </span>
                  </div>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
    <a href="/bets" class="btn btn-success" @click="savePlayerBet()">Realizar apuesta</a><br><br>
    <a href="/bets" class="btn btn-success" v-if="this.bets.estado=='PENDIENTE'" @click="checkPlayerBet()">Comprobar apuesta</a><br><br>
    <a href="/bets" class="btn btn-success">Volver</a>
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
      result: [],
      playerBets: []
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

    getPlayerBetByBetId(){
      PlayerBetService.getPlayerBetByBetId(this.$route.params.betId).then((response) => {
        this.playerBets = response.data;
        console.log(this.playerBets);
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
      if(this.bets.betType=='WINNER'){
        PlayerBetService.postPlayerBet(this.matchBet).then((response) => {
            console.log(response);
      })
      } else if(this.bets.betType=='RESULT'){
        let result = this.result;
        result[0]=this.$route.params.betId;
        for(let i=0;i<this.matches.length;i++){
          let homeResult = document.getElementsByName("goalsHomeTeam").item(i).value;
          let awayResult = document.getElementsByName("goalsAwayTeam").item(i).value;
          result[result.length]=homeResult;
          result[result.length]=awayResult;
          console.log(this.result);
        }
        PlayerBetService.postPlayerBetResult(this.result).then((response) => {
          console.log(response);
          console.log(this.result);
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
        this.getPlayerBetByBetId()
    }
}
</script>

<style>
.acierto {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 150px; 
   height: 50px; 
   border: 3px solid #555;
   background-color: rgb(0, 255, 0);
 }

.fallo {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 150px; 
   height: 50px; 
   border: 3px solid #555;
   background-color: rgb(255, 0, 0);
 }

.soloGol {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 150px; 
   height: 50px; 
   border: 3px solid #555;
   background-color: rgb(255, 255, 0);
 }
</style>