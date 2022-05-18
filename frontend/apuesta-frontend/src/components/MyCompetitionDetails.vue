<template>
<div class="container">
    <br>
    <br>
    <div class="equipos" v-for = "team in teams" v-bind:key = "team.id">
        <router-link :to="'/teams/' + team.id + '/nextMatches'"><img :src="team.emblemUrl" alt="" width="40" height="40"></router-link>
    </div>
    <br><br><br><br><br>
    <div class="container1">
        <h4 class="text-center"> CLASIFICACION</h4><br>
        <table class="table table-striped">
        <thead>
            <th> </th>
            <th> EQUIPO </th>
            <th> PUNTOS </th>
            <th> PJ </th>
            <th> PG </th>
            <th> PE </th>
            <th> PP </th>

        </thead>
        <tbody v-for = "standing in standings" v-bind:key = "standing.id">
            <tr v-for = "team in standing.teams" v-bind:key = "team.id">
                <td>{{ standing.position }}</td>
                <td><img :src="team.emblemUrl" alt="" width="30" height="30">{{ team.name }}</td>
                <td>{{ standing.puntos }}</td>
                <td>{{ standing.pj }}</td>
                <td>{{ standing.pg }}</td>
                <td>{{ standing.pe }}</td>
                <td>{{ standing.pp }}</td>
            </tr>  
        </tbody>
        </table>
    </div>

    <div class="container2">
        <h4 class="text-center"> GOLEADORES</h4><br>
        <table class="table table-striped">
        <thead>
            <th> JUGADOR</th>
            <th> EQUIPO </th>
            <th> GOLES </th>
        </thead>
        <tbody>
            <tr v-for = "scorer in scorers" v-bind:key = "scorer.id">
                <td>{{ scorer.footballPlayer }}</td>
                <td>{{ scorer.playerTeam }}</td>
                <td>{{ scorer.goals }}</td>
            </tr>  
        </tbody>
        </table>
    </div>
</div>

    
</template>

<script>

import TeamService from '../services/TeamService'; 
import StandingService from '../services/StandingService';   
import ScorerService from '../services/ScorerService';

export default {
    name: 'MyCompetitionDetails',
    data(){
        return {
            teams: [],
            standings: [],
            scorers: []
        }
    },
    methods: {
        getTeamsByCompetition(){
            TeamService.getTeamsByCompetition(this.$route.params.competitionId).then((response) => {
                this.teams = response.data;
            })
        },

        getClasification(){
            StandingService.getClasificationByCompetition(this.$route.params.competitionId).then((response) =>{
                this.standings = response.data;
            });
        },

        getScorersByCompetition(){
            ScorerService.getScorersByCompetition(this.$route.params.competitionId).then((response) => {
                this.scorers = response.data;
                console.log(this.scorers);
            })
        }
    },
    created() {
        this.getTeamsByCompetition()
        this.getClasification()
        this.getScorersByCompetition()
    }
}


</script>

<style>

.equipos{
    float:left;
}

.container1 {
    float:left;
    margin-right: 15%;
    padding-bottom: 70px;
}
.container2 {
    float:left;
    padding-bottom: 70px;
}
</style>
