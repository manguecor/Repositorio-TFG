<template>
    <div class="container">
    <br><span class="titulos"> APUESTAS REALIZADAS </span><br><br>
    <table class="table table-striped">
        <thead>
            <th style="width:35%"> EQUIPO LOCAL </th>
            <th style="width:25%"> EQUIPO VISITANTE </th>
            <th style="width:20%" v-if="this.status==true"> RESULTADO </th>
            <th style="width:20%"> APUESTA </th>
        </thead>
        <tbody>
            <tr v-for= "match in matches" v-bind:key= "match.id">
                <td>
                    {{match.homeTeam}}
                </td>
                <td>
                    {{match.awayTeam}}
                </td>
                <td v-if="match.status=='FINISHED'">
                  <a type="checkbox" v-if="match.status=='FINISHED' && match.result=='HOME_TEAM'">EQUIPO LOCAL</a>
                  <a type="checkbox" v-if="match.status=='FINISHED' && match.result=='AWAY_TEAM'">EQUIPO VISITANTE</a>
                  <a type="checkbox" v-if="match.status=='FINISHED' && match.result=='DRAW'">EMPATE</a>
                  <a type="checkbox" v-if="match.status=='FINISHED' && match.result!='HOME_TEAM' && match.result!='AWAY_TEAM' && match.result!='DRAW'">{{match.result}}</a>
                </td>
                <td v-if="this.playerBets.length!=0">
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <button v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result && playerBet.bet.betType=='RESULT'" class="acierto" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED' && playerBet.bet.betType=='RESULT'" disabled>
                      {{playerBet.playerResult}}
                    </span>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[0] == match.result[0] && playerBet.bet.betType=='RESULT'" class="soloGol" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult[2] == match.result[2] && playerBet.bet.betType=='RESULT'" class="soloGol" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result && playerBet.bet.betType=='RESULT'" class="fallo" disabled>
                      {{playerBet.playerResult}}
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && (playerBet.playerResult[0]-playerBet.playerResult[2])==(match.result[0]-match.result[2])" class="difGol" disabled>
                      {{playerBet.playerResult}}
                    </button>
                  </div>
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <button v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result && playerBet.playerResult=='HOME_TEAM'" class="acierto" disabled>
                      EQUIPO LOCAL
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result && playerBet.playerResult=='AWAY_TEAM'" class="acierto" disabled>
                      EQUIPO VISITANTE
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result && playerBet.playerResult=='DRAW'" class="acierto" disabled>
                      EMPATE
                    </button>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED' && playerBet.playerResult=='HOME_TEAM'" disabled>
                      EQUIPO LOCAL
                    </span>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED' && playerBet.playerResult=='AWAY_TEAM'" disabled>
                      EQUIPO VISITANTE
                    </span>
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED' && playerBet.playerResult=='DRAW'" disabled>
                      EMPATE
                    </span>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result && playerBet.playerResult=='HOME_TEAM'" class="fallo" disabled>
                      EQUIPO LOCAL
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result && playerBet.playerResult=='AWAY_TEAM'" class="fallo" disabled>
                      EUIPO VISITANTE
                    </button>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result && playerBet.playerResult=='DRAW'" class="fallo" disabled>
                      EMPATE
                    </button>
                  </div>
                </td>
            </tr>
        </tbody>
    </table>
    <a style="margin-left: 14px" class="btn btn-success" @click="$router.go(-1)">Volver</a>
    </div>
</template>

<script>
import MatchService from '../services/MatchService';
import PlayerBetService from "../services/PlayerBetService";

export default {
  name: "MyPlayerBetsByPlayer",
  data(){
    return {
      playerBets: [],
      matchesId: [],
      matchesAPI: [],
      matches: [],
      status: null
    }
  },
  methods: {

    getPlayerBetsByPlayer() {
        PlayerBetService.getPlayerBetsByPlayer(this.$route.params.playerId).then((response) => {
            this.playerBets = response.data;
            for(let value in this.playerBets){
            MatchService.getMatchByAPIId(this.playerBets[value].matchId).then((response) => {
            let matches = this.matches;
            this.matches[matches.length] = response.data;
            for(let value in this.matches){
              if(this.matches[value].status=='FINISHED'){
                this.status = true;
              } else {
                this.status = false;
              }
            }
            })
            }
        })
    },
  },

  created() {
        this.getPlayerBetsByPlayer()
    }
}

</script>


