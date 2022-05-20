<template>
<div class="container">
    <h1 class="text-center"> Leagues list </h1>
    <table class="table table-striped">
        <thead>

            <th> Name </th>
            <th> Code </th>
        </thead>
        <tbody>
            <tr v-for = "league in leagues" v-bind:key = "league.id">
                
                <!--<td> {{league.id}} </td>-->
                <td> {{league.name}} </td>
                <td> {{league.code}} </td>
                <td> <a :href="'/leagues/' + league.id + '/bets/'" class="btn btn-success">Acceder a la liga</a></td>
                <td> <a href="/leagues/myLeagues" @click="delete_league(league.id)"  class="btn btn-success">Borrar liga</a> </td>
            </tr>
            
        </tbody>
    </table>
    <a href="/leagues/new"  class="btn btn-success">Crear liga</a>
    <a href="/leagues/join" class="btn btn-success">Unirse a una liga</a>
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
        getMyLeagues(){
            LeagueService.listMyLeagues().then((response) =>{
                this.leagues = response.data;
                console.log(this.leagues)
            });
        },
        delete_league(leagueId){
            LeagueService.deleteLeague(leagueId).then(data => {
                console.log(data);
            });
        }
    },
    created() {
        this.getMyLeagues()
    }
}
</script>