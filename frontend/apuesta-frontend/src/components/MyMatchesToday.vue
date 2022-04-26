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

import MatchService from '../services/MatchService';    

export default {
    name: 'MyMatchesToday',
    data(){
        return {
            matches: []
        }
        
    },
    methods: {
        getMatchesToday(){
            MatchService.getMatchesToday().then((response) => {
                this.matches = response.data;
            })
        }
    },
    created() {
        this.getMatchesToday()
    }
}
</script>