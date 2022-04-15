<template>
<div class="container">
    <br><h2 class="text-center"> Próximos partidos del equipo </h2><br>
    <table class="table table-striped">
        <thead>
            <!--<th> Match Id </th>-->
            <th> Equipo local </th>
            <th> Equipo visitante </th>
            <th> Competición </th>
            <th> Fecha del partido </th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                
                <!--<td> {{match.id}} </td>-->
                <td> {{match.homeTeam}}</td>
                <td> {{match.awayTeam}}</td>
                <td> {{match.competition}} </td>
                <td> {{match.match_date}} </td>
            </tr>
            
        </tbody>
    </table>
</div>

    
</template>

<script>

import CompetitionService from '../services/CompetitionService';    

export default {
    name: 'MyMatches',
    data(){
        return {
            matches: []
        }
        
    },
    methods: {
        getNextMatchesByTeam(){
            CompetitionService.getNextMatchesByTeam(this.$route.params.teamId).then((response) => {
                this.matches = response.data;
            })
        }
    },
    created() {
        this.getNextMatchesByTeam()
    }
}
</script>
