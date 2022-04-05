<template>
<div class="container">
    <h1 class="text-center"> Competitions list </h1>
    <table class="table table-striped">
        <thead>
            <th> Competitions Id </th>
            <th> Name </th>
            <th> EmblemUrl </th>
        </thead>
        <tbody>
            <tr v-for = "competition in competitions" v-bind:key = "competition.id">
                
                <td> {{competition.id}} </td>
                <td> {{competition.name}} </td>
                <td> {{competition.emblemUrl}} </td>
            </tr>
            
        </tbody>
    </table>
</div>

    
</template>

<script>

import CompetitionService from '../services/CompetitionService';    

export default {
    name: 'MyCompetitions',
    data(){
        return {
            competitions: []
        }
        
    },
    methods: {
        getCompetitions(){
            CompetitionService.getCompetitions().then((response) =>{
                this.competitions = response.data;
            });
        }
    },
    created() {
        this.getCompetitions()
    }
}


</script>

