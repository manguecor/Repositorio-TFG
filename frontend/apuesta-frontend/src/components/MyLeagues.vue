<template>
<div class="container">
    <h1 class="text-center"> Leagues list </h1>
    <table class="table table-striped">
        <thead>
            <th> Leagues Id </th>
            <th> Name </th>
            <th> Code </th>
        </thead>
        <tbody>
            <tr v-for = "league in leagues" v-bind:key = "league.id">
                
                <td> {{league.id}} </td>
                <td> {{league.name}} </td>
                <td> {{league.code}} </td>
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
        getLeagues(){
            LeagueService.listAll().then((response) =>{
                this.leagues = response.data;
            });
        }
    },
    created() {
        this.getLeagues()
    }
}


</script>

