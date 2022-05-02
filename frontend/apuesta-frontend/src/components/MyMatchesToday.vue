<template>
<div class="container">
    <br><h2 class="text-center"> Partidos de hoy </h2><br>
    <table class="table table-striped">
        <thead>
            <!--<th> Match Id </th>-->
            <th> Equipo local </th>
            <th> Equipo visitante </th>
            <th> Competición </th>
            <th> Fecha del partido </th>
            <th> </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <!--<td> {{match.id}} </td>-->
                <td> {{match.homeTeam}}</td>
                <td> {{match.awayTeam}}</td>
                <td> {{match.competition}} </td>
                <td> {{match.match_date}} </td>
                <td> <a class="btn btn-success" v-on:click="addMatch(match.id)">Añadir partido</a></td>
                <td> <a href="/" class="btn btn-success" v-on:click="saveBet">Guardar apuesta</a></td>
            </tr>
            
        </tbody>
    </table>
   
</div>

    
</template>

<script>

import MatchService from '../services/MatchService'; 
import BetService from "../services/BetService";   

export default {
    name: 'MyMatchesToday',
    data(){
        return {
            matches: [],
            matchesId: []
        }
        
    },
    methods: {
        getMatchesToday(){
            MatchService.getMatchesToday().then((response) => {
                this.matches = response.data;
            })
        },
        
        addMatch(matchId){
            let matchesId = this.matchesId;
            if(!matchesId.includes(matchId) && matchesId.length<=5){
                matchesId[0]=localStorage.getItem("username");
                matchesId[matchesId.length]=matchId;
                console.log(matchesId);
            }
                 
        },

        saveBet() {
            BetService.postBet(this.matchesId).then((response) => {
            console.log(response);
            console.log(localStorage.getItem("username"));
            })
        }
    },
    created() {
        this.getMatchesToday()
    }
}
</script>