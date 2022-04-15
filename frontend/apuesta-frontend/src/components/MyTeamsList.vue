<template>
<div class="container">
    <br><h2 class="text-center"> Equipos de la competición </h2><br>
    <table class="table table-striped">
        <thead>
            <!--<th> Teams Id </th>-->
            <th> Nombre </th>
            <th> Escudo </th>
            <th> Año de fundación </th>
            <th> Estadio </th>
        </thead>
        <tbody>
            <tr v-for = "team in teams" v-bind:key = "team.id">
                
                <!--<td> {{team.id}} </td>-->
                <td><router-link :to="'/teams/' + team.id + '/nextMatches'">{{team.name}}</router-link></td>
                <td> <img :src="team.emblemUrl" alt="" width="40" height="40"> </td>
                <td> {{team.foundation_year}} </td>
                <td> {{team.stadium}} </td>
            </tr>
            
        </tbody>
    </table>
</div>

    
</template>

<script>

import CompetitionService from '../services/CompetitionService';    

export default {
    name: 'MyTeams',
    data(){
        return {
            teams: []
        }
        
    },
    methods: {
        getTeamsByCompetition(){
            CompetitionService.getTeamsByCompetition(this.$route.params.competitionId).then((response) => {
                this.teams = response.data;
            })
        }
    },
    created() {
        this.getTeamsByCompetition()
    }
}


</script>
