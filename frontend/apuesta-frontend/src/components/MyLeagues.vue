<template>
<div class="container">
    <br>
    <span class="titulos"> MIS LIGAS </span><br><br>
    <table class="table table-striped">
        <thead>
            <th> NOMBRE </th>
            <th> CODIGO </th>
            <th> </th>
        </thead>
        <tbody>
            <tr v-for = "league in leagues" v-bind:key = "league.id">
                
                <!--<td> {{league.id}} </td>-->
                <td> {{league.name}} </td>
                <td> {{league.code}} </td>
                <td> <a :href="'/leagues/' + league.id + '/bets/'" class="btn btn-success">Acceder a la liga</a></td>
            </tr>
            
        </tbody>
    </table>
    <a href="/leagues/new"  class="btn btn-success">Crear liga</a>
    <a style="margin-left: 14px" href="/leagues/join" class="btn btn-success">Unirse a una liga</a>
    <br>
    <br>
    <a href="/" class="btn btn-success">Volver</a>
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
        }
    },
    created() {
        this.getMyLeagues()
    }
}
</script>