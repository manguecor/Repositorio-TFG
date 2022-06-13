<template>
<div class="container">
    <br><span class="titulos"> PARTIDOS DE HOY </span><br><br>
    <input type="date" id="date" name="date">
    <input style="margin-left:14px" type="submit" value="Buscar" class="btn btn-success" @click="getMatchesByDate()">
    <br><br>
    <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL </th>
            <th> EQUIPO VISITANTE </th>
            <th> COMPETICION </th>
            <th> FECHA </th>
            <th> ESTADO </th>
            <th v-if="isAuthenticated"> </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <td> {{match.homeTeam}}</td>
                <td> {{match.awayTeam}}</td>
                <td> {{match.competition}} </td>
                <td> {{match.match_date}} </td>
                <td v-if="match.status=='SCHEDULED'"> PROGRAMADO</td>
                <td v-else-if="match.status=='FINISHED'"> TERMINADO</td>
                <td v-if="isAuthenticated"> <a class="btn btn-success" v-if="match.status=='SCHEDULED'" v-on:click="addMatch(match.id)">Añadir partido</a></td>
            </tr>
        </tbody>
    </table>
    <form @submit="saveBet">
        <p v-if="errors.length">
        <b>Por favor, corriga el(los) siguiente(s) error(es): </b>
          <ul>
            <li v-for="error in errors" v-bind:key="error.id">{{error}}</li>
          </ul>
        </p>
    <div class="form-group" v-if="isAuthenticated">
    <label>Tipo de apuesta:</label>
        <select name="betType" id="betType">
            <option value="WINNER">Ganador del partido</option>
            <option value="RESULT">Resultado exacto</option>
        </select><br><br>
    </div>
    <div class="form-group" v-if="isAuthenticated">
        <label for="description">Descripción de de la apuesta:</label>
            <input id="description" v-model="description" placeholder="Descripción de la apuesta" type="text" class="form" required>
    </div>
    <br>
    <div class="form-group" v-if="isAuthenticated">
        <label for="leagueName">Nombre de la liga:</label>
            <select v-model="leagueName" id="leagueName" required>
            </select>
                
    </div>
    <br>
    <div class="form-group" v-if="isAuthenticated">
        <p>
           <input type="submit" value="Guardar apuesta" class="btn btn-success">
        </p>
    </div>
    </form>
    <a href="/" class="btn btn-success">Volver</a>
    

   
</div>

    
</template>

<script>
import MatchService from '../services/MatchService'; 
import BetService from "../services/BetService"; 
import LoginService from "../services/LoginService"; 
import LeagueService from "../services/LeagueService";

export default {
    name: 'MyMatchesToday',
    data(){
        return {
            matches: [],
            matchesId: [],
            description: null,
            leagueName: null,
            isAuthenticated: LoginService.isUserLoggedIn(),
            myLeagues: [],
            errors: [],
            date: null
        }
        
    },
    methods: {
        getMatchesToday(){
            MatchService.getMatchesToday().then((response) => {
                this.matches = response.data;
            })
        },

        getMyLeaguesNames(){
            LeagueService.listMyLeagues().then((response) =>{
                var leagues = response.data;
                for(let value in leagues) {
                    this.myLeagues[value] = leagues[value].name;
                }
                var select = document.querySelector("#leagueName");
                for(let value in this.myLeagues) {
                    var option = document.createElement("option");
                    option.value = this.myLeagues[value];
                    option.text = this.myLeagues[value];
                    select.append(option);
                }
            });
        },
        
        addMatch(matchId){
            let matchesId = this.matchesId;
            if(!matchesId.includes(matchId) && matchesId.length<=5){
                matchesId[matchesId.length]=matchId;
            }
                 
        },
        saveBet: function (e) {
            this.errors = [];
            
            let matchesId = this.matchesId;
            if(matchesId.length<2) {
                this.errors.push('Tienes que elegir al menos dos partidos')
            }

            e.preventDefault();
            
            if(this.errors.length==0) {
                let betType = document.getElementById("betType");
                let betTypeValue = betType.options[betType.selectedIndex].value;
                let description = this.description;
                let leagueName = this.leagueName;
                matchesId.unshift(leagueName);
                matchesId.unshift(description);
                matchesId.unshift(betTypeValue);
                BetService.postBet(this.matchesId).then(
                window.location.href = "/"
                )
            }   
        },

        getMatchesByDate(){
            this.date = document.getElementById("date").value;
            MatchService.getMatchesByDate(this.date).then((response) => {
                this.matches = response.data;
            })
        }
                  
    },
    created() {
        this.getMatchesToday()
        this.getMyLeaguesNames()
    }
}
</script>

<style>
    .container{
        padding-bottom: 70px;
    }
</style>