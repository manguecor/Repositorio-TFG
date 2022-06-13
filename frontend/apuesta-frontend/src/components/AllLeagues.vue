<template>
<div class="container">
    <br>
    <span class="titulos"> LISTA DE LIGAS </span><br><br>
    <table class="table table-striped">
        <thead>
            <th> NOMBRE </th>
            <th> CODIGO </th>
            <th> </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "league in leagues" v-bind:key = "league.id">
                <td> {{league.name}} </td>
                <td> {{league.code}} </td>
                <td> <a :href="'/leagues/' + league.id + '/bets/'" class="btn btn-success">Acceder a la liga</a></td>-
                <td> <a href="/leagues" @click="delete_league(league.id)"  class="btn btn-success">Borrar liga</a> </td>
            </tr>
            
        </tbody>
    </table>
    <a href="/leagues/new"  class="btn btn-success">Crear liga</a>
    <br>
    <br>
    <a href="/" class="btn btn-info">Volver</a>
</div>
    
</template>

<script>
import LeagueService from '../services/LeagueService.js';    
export default {
    name: 'MyLeagues',
    data(){
        return {
            leagues: []
        }
        
    },
    methods: {
        getAllLeagues(){
            LeagueService.listAll().then((response) =>{
                this.leagues = response.data;
            });
        },
        delete_league(leagueId){
            LeagueService.deleteLeague(leagueId);
        }
    },
    created() {
        this.getAllLeagues()
    }
}
</script>