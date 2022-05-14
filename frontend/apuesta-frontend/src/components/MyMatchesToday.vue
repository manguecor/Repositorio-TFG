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
            <th> Estado </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <!--<td> {{match.id}} </td>-->
                <td> {{match.homeTeam}}</td>
                <td> {{match.awayTeam}}</td>
                <td> {{match.competition}} </td>
                <td> {{match.match_date}} </td>
                <td> {{match.status}}</td>
                <td> <a class="btn btn-success" v-if="match.status=='SCHEDULED'" v-on:click="addMatch(match.id)">Añadir partido</a></td>
            </tr>
        </tbody>
    </table>
    <div class="form-group">
        <label for="description">Descripción de de la apuesta:</label>
            <input id="description" v-model="name" placeholder="Descripción de la apuesta" type="text" class="form">
    </div>
    <br>
    <div class="form-group">
        <label for="leagueId">Nombre de la liga:</label>
            <input id="leagueId" v-model="leagueId" placeholder="Nombre de la liga" type="text" class="form">
    </div>
    <br>
    <a href="/" class="btn btn-success" v-on:click="saveBet">Guardar apuesta</a>
    
   
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
            matchesId: [],
            name: null,
            leagueId: null
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
                matchesId[matchesId.length]=matchId;
                console.log(matchesId);
            }
                 
        },
        saveBet() {
            let matchesId = this.matchesId;
            let name = this.name;
            let leagueId = this.leagueId;
            matchesId.unshift(leagueId);
            matchesId.unshift(name);
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