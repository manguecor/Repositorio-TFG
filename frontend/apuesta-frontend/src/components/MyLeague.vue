<template>
<div class="container">
    <h1 class="text-center"> League {{league.id}} </h1>
    <table class="table table-striped">
        <thead>
            <th> Name </th>
            <th> Code </th>
        </thead>
        <tbody>
            
                
                <!--<td> {{league.id}} </td>-->
                <td> {{league.name}} </td>
                <td> {{league.code}} </td>
                <td> <a href="/leagues/myLeague" @click="delete_league" class="btn btn-success">Borrar liga</a> </td>
            
            
        </tbody>
    </table>
    <a href="/leagues" class="btn btn-info">Volver</a>
</div>
    
</template>

<script>

import LeagueService from '../services/LeagueService.js';    

export default {
    name: 'MyLeagues',
    data(){
        return {
            league: {
                leagueId: null,
                name: null,
                code: null
            }
            
        }
        
    },
    methods: {
        get_league() {
            LeagueService.getLeague(this.$route.params.leagueId).then(data => {
                console.log(data);
            });
        }
    },
    created() {
        this.get_league()
    }
}


</script>