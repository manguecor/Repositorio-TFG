<template>
<div class="container">
    <br>
    <br>
    <div class="equipos" v-for = "team in teams" v-bind:key = "team.id">
        <router-link :to="'/teams/' + team.id + '/nextMatches'"><img :src="team.emblemUrl" alt="" width="40" height="40"></router-link>
    </div>
    <div class="container">
        <table class="table table-striped" style="width:45%" border=3>
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
</div>

    
</template>

<script>

import TeamService from '../services/TeamService'; 
import StandingService from '../services/StandingService';   

export default {
    name: 'MyCompetitionDetails',
    data(){
        return {
            teams: [],
            standings: []
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
        }
    },
    created() {
        this.getTeamsByCompetition()
        this.getClasification()
    }
}


</script>

<style>

.equipos{
    overflow: hidden;
    margin-top: 0px;
    block-size: 70px;
    float:left;
    text-align: center;
    margin-left: 0%;
    margin-right: 0%;
}
</style>
