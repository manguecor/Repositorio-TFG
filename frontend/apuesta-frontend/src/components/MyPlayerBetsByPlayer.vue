<template>
    <div class="container">
    <br><span class="titulos"> APUESTAS REALIZADAS </span><br><br>
    <table class="table table-striped">
        <thead>
            <th style="width:35%"> EQUIPO LOCAL </th>
            <th style="width:25%"> EQUIPO VISITANTE </th>
            <th style="width:20%"> RESULTADO </th>
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
                <td>
                    <a type="checkbox" v-if="match.status=='FINISHED'">{{match.result}}</a>
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
                  </div>
                  <div v-for= "playerBet in playerBets" v-bind:key= "playerBet.id">
                    <button v-if="playerBet.matchId == match.api_id && playerBet.playerResult == match.result && playerBet.bet.betType=='WINNER'" class="acierto" disabled>
                      {{playerBet.playerResult}}
                    </button> 
                    <span v-else-if="playerBet.matchId == match.api_id && match.status=='SCHEDULED' && playerBet.bet.betType=='WINNER'" disabled>
                      {{playerBet.playerResult}}
                    </span>
                    <button v-else-if="playerBet.matchId == match.api_id && playerBet.playerResult != match.result && playerBet.bet.betType=='WINNER'" class="fallo" disabled>
                      {{playerBet.playerResult}}
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
            console.log(this.playerBets);
            })
            }
            /*for(let value in this.playerBets){
                MatchService.getMatchByAPIId(this.playerBets[value].matchId).then((response) => {
                console.log(this.playerBets[value].matchId);
                let matchesAPI = this.matchesAPI;
                this.matchesAPI[matchesAPI.length] = response.data;
                console.log(this.matchesAPI);
                //let matches = this.matches;
                //this.matches[matches.length] = matchesAPI[value];
                //console.log(this.matches);
            })
            }
            /*for(let value2 in this.matchesAPI){
                let matches = this.matches;
                let matchesAPI = this.matchesAPI;
                this.matches[matches.length] = matchesAPI[value2][0];
                console.log(this.matchesAPI[value2][0]);
            }
            }
            console.log(this.matchesAPI);
            for(let value2 in this.matchesAPI){
              let matches = this.matches;
              this.matches[matches.length] = this.matchesAPI[value2][0];
              console.log(this.matches);
            }*/
        })
    },
  },

  created() {
        this.getPlayerBetsByPlayer()
        //this.getMatchesById()
    }
}

</script>
