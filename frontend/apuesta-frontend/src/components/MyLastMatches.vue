<template>
<div class="container">
    <br><h2 class="text-center"> Últimos resultados </h2><br>
    <table class="table table-striped" style="width:35%" border=5>
        <tbody>
            <tr v-for = "match in matches" v-bind:key = "match.id">
                
                <!--<td> {{match.id}} </td>-->
                <td style="width:30%"> {{match.homeTeam}}</td>
                <td style="width:10%"> {{match.result}}</td>
                <td style="width:30%"> {{match.awayTeam}}</td>
                <td style="width:30%"> {{match.competition}} </td>
            </tr>
            
        </tbody>
    </table>
</div>

    
</template>

<script>

import MatchService from '../services/MatchService';    

export default {
    name: 'MyLastMatches',
    data(){
        return {
            matches: []
        }
        
    },
    methods: {
        getLastMatches(){
            MatchService.getLastMatches(this.$route.params.teamId).then((response) => {
                this.matches = response.data;
            })
        }
    },
    created() {
        this.getLastMatches()
    }
}
</script>