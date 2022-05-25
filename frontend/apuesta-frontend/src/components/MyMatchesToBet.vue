<template>
  <div class="container">
    <br><span class="titulos"> DETALLES DE LA APUESTA </span><br><br>
    <div v-if="this.bets.betType=='WINNER'">
    <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL </th>
            <th> </th>
            <th> EQUIPO VISITANTE</th>
            <th v-if="this.status==true"> RESULTADO</th>
            <th v-if="this.playerBets.length!=0"> MI APUESTA</th>
        </thead>
        <tbody>
            <tr class="#botones.focus" v-for = "match in matches" v-bind:key = "match.id" >
                <td> 
                    <a type="checkbox" v-if="match.status=='SCHEDULED' && this.playerBets.length!=0">{{match.homeTeam}}</a>
                    <button id="botones" v-if="match.status=='SCHEDULED' && this.playerBets.length==0" @click="saveHomeTeam(match.api_id)">{{match.homeTeam}}</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.homeTeam}}</a>
                </td>
                <td> 
                    <a type="checkbox" v-if="match.status=='SCHEDULED' && this.playerBets.length!=0">-</a>
                    <button id="botones" v-if="match.status=='SCHEDULED' && this.playerBets.length==0" @click="saveDraw(match.api_id)">-</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">-</a>
                </td>
                <td>
                    <a type="checkbox" v-if="match.status=='SCHEDULED' && this.playerBets.length!=0">{{match.awayTeam}}</a>
                    <button id="botones" v-if="match.status=='SCHEDULED' && this.playerBets.length==0" @click="saveAwayTeam(match.api_id)">{{match.awayTeam}}</button>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.awayTeam}}</a>
                </td>
                <td v-if="match.status=='FINISHED'">
                  <a type="checkbox" v-if="match.status=='FINISHED'">{{match.result}}</a>
                </td>
                <td v-if="this.playerBets.length!=0">
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <button v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result" class="acierto" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED'" disabled>
                      {{playerBet.playerResult}}
                    </span>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result" class="fallo" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    
                  </div>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
    <div v-if="this.bets.betType=='RESULT'">
      <table class="table table-striped">
        <thead >
            <th> EQUIPO LOCAL </th>
            <th> </th>
            <th> EQUIPO VISITANTE</th>
            <th v-if="this.status==true"> RESULTADO</th>
            <th v-if="this.playerBets.length!=0"> MI APUESTA</th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <td> 
                    <a type="checkbox" v-if="match.status=='SCHEDULED'">{{match.homeTeam}}</a>
                    <input type="number" id="goalsHomeTeam" name="goalsHomeTeam" min="0" max="15" v-if="match.status=='SCHEDULED' && this.playerBets.length==0">
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.homeTeam}}</a>
                </td>
                <td> 
                    <a type="checkbox">-</a>
                </td>
                <td> 
                    <input type="number" id="goalsAwayTeam" name="goalsAwayTeam" min="0" max="15" v-if="match.status=='SCHEDULED'  && this.playerBets.length==0">
                    <a type="checkbox" v-if="match.status=='SCHEDULED'">{{match.awayTeam}}</a>
                    <a type="checkbox" v-else-if="match.status!='SCHEDULED'">{{match.awayTeam}}</a>
                </td>
                <td v-if="match.status=='FINISHED'">
                  <a type="checkbox" v-if="match.status=='FINISHED'">{{match.result}}</a>
                </td>
                <td v-if="this.playerBets.length!=0">
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <button v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result" class="acierto" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED'" disabled>
                      {{playerBet.playerResult}}
                    </span>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[0] == match.result[0]" class="soloGol" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[2] == match.result[2]" class="soloGol" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result" class="fallo" disabled>
                      {{playerBet.playerResult}}
                    </button>
                  </div>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
    <a :href="'/leagues/' + leagueId + '/bets/'"  class="btn btn-success" v-if="this.playerBets.length==0" @click="savePlayerBet()">Realizar apuesta</a>
    <a style="margin-left: 14px" :href="'/leagues/' + leagueId + '/bets/'" class="btn btn-success" v-if="this.bets.estado=='PENDIENTE' && this.status==true" @click="checkPlayerBet()">Comprobar apuesta</a>
    <a style="margin-left: 14px" :href="'/leagues/' + leagueId + '/bets/'" class="btn btn-success" v-if="this.bets.estado=='COMPROBADA'" @click="closeBet()">Cerrar apuesta</a>
    <a style="margin-left: 14px" :href="'/leagues/' + leagueId + '/bets/'" class="btn btn-success">Volver</a>
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
      playerBets: [],
      leagueId: null,
      status: null
    }
  },
  methods: {
    
    getMatches() {
        MatchService.getMatchesByBetId(this.$route.params.betId).then((response) => {
            this.matches = response.data;
            console.log(this.matches);
            for(let value in this.matches){
              if(this.matches[value].status=='FINISHED'){
                this.status = true;
              } else {
                this.status = false;
              }
            }
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
    },

    getLeagueIdByBet() {
      BetService.getLeagueIdByBet(this.$route.params.betId).then((response)=>{
        this.leagueId = response.data;
      });
    },

    closeBet(){
      BetService.closeBet(this.$route.params.betId).then((response) => {
        console.log(response);
      });
    }
  },
  created() {
        this.getMatches()
        this.getBetByBetId()
        this.getPlayerBetByBetId()
        this.getLeagueIdByBet()
    }
}
</script>

<style>
.acierto {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 140px; 
   height: 35px; 
   border: 2px solid #555;
   background-color: rgb(0, 255, 0);
   color: black;
   border-radius: 6px;
 }

.fallo {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 140px; 
   height: 35px; 
   border: 2px solid #555;
   background-color: rgb(255, 0, 0);
   color: black;
   border-radius: 6px;
 }

.soloGol {
   font: rgb(0, 0, 0);
   font-weight: bold;
   width: 140px; 
   height: 35px; 
   border: 2px solid #555;
   background-color: rgb(255, 255, 0);
   color: black;
   border-radius: 6px;
 }

 .table {
   background-color: white;
   color: black;
 }

 .table thead {
   background-color: #3bd03d;
   border-bottom: solid 5px white;
   color: white;
   height: 40px;
   text-align: center;
   text-decoration: none;
   vertical-align: middle;
 }

 .table tbody {
   background-color: white;
   border-bottom: solid 5px white;
   color: black;
   height: 40px;
   text-align: center;
   text-decoration: none;
   vertical-align: middle;
 }

 .titulos {
   color: black;
   text-align: center;
   text-decoration:none;
   font-size: 25px;
 }

  #botones {
    width: 240px; 
    height: 35px; 
    border: 2px solid #555;
    color: black;
    border-radius: 6px;
    background-color: white;
  }
  
  #botones:focus {
    background-color: #3bd03d;
    color: black;
  }

</style>