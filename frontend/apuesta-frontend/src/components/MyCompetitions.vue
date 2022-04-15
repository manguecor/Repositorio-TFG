<template>
<div class="container">
    <br><h2 class="text-center"> Listado de competiciones </h2><br>
    <table class="table table-striped">
        <thead>
            <!--<th> Competitions Id </th>-->
            <th> Nombre </th>
            <th> Logo </th>
        </thead>
        <tbody>
            <tr v-for = "competition in competitions" v-bind:key = "competition.id">
                <!--<td> {{competition.id}} </td>-->
                <td><router-link :to="'/teams/' + competition.id">{{competition.name}}</router-link></td>
                <td> <img :src="competition.emblemUrl" alt="" width="40" height="40"> </td>
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
        this.getCompetitions();
    }
}


</script>

