<template>
<div class="container">
    <br><h2 class="text-center"> PROXIMOS PARTIDOS</h2><br>
    <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL</th>
            <th> EQUIPO VISITANTE</th>
            <th> COMPETICION </th>
            <th> FECHA DEL PARTIDO</th>
        </thead>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                <td> {{match.homeTeam}}</td>
                <td> {{match.awayTeam}}</td>
                <td> {{match.competition}} </td>
                <td> {{match.match_date}} </td>
            </tr>
            
        </tbody>
    </table>
    <br><br><h2 class="text-center"> ULTIMOS RESULTADOS</h2><br>
    <table class="table table-striped">
        <thead>
            <th> EQUIPO LOCAL</th>
            <th> RESULTADO </th>
            <th> EQUIPO VISITANTE</th>
            <th> COMPETICION </th>
        </thead>
        <tbody>
            <tr v-for = "match in lastMatches" v-bind:key = "match.id">
                <td style="width:30%"> {{match.homeTeam}}</td>
                <td style="width:10%"> {{match.result}}</td>
                <td style="width:30%"> {{match.awayTeam}}</td>
                <td style="width:30%"> {{match.competition}} </td>
            </tr>
        </tbody>
    </table>
    <a @click="$router.go(-1)" class="btn btn-success">Volver</a><br><br>
</div>

    
</template>

<script>

import MatchService from '../services/MatchService';    

export default {
    name: 'MyNextMatches',
    data(){
        return {
            matches: [],
            lastMatches: []
        }
        
    },
    methods: {
        getNextMatchesByTeam(){
            MatchService.getNextMatchesByTeam(this.$route.params.teamId).then((response) => {
                this.matches = response.data;
            })
        },

        getLastMatches(){
            MatchService.getLastMatches(this.$route.params.teamId).then((response) => {
                this.lastMatches = response.data;
            })
        }
    },
    created() {
        this.getNextMatchesByTeam()
        this.getLastMatches()
    }
}
</script>
