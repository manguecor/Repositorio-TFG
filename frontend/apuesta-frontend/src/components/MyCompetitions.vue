<template>
    <div class="container marketing">

    <div class="row" v-for = "competition in competitions" v-bind:key = "competition.id">
      <div class="col-sm">
        <p><router-link :to="'/teams/' + competition.id"><img :src="competition.emblemUrl" alt="" width="80" height="80"></router-link></p>
        <p><router-link :to="'/teams/' + competition.id">{{competition.name}}</router-link></p>
      </div>
    </div>
    </div><br>
    
</template>

<script>
import CompetitionService from '../services/CompetitionService';    

export default {
    
    name: 'MyCompetitions',
    data(){
        return {
            competitions: [],
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

<style>

.row{
    overflow: hidden;
    margin-top: 5%;
    block-size: 200px;
    float:left;
    text-align: center;
    margin-left: 0%;
    margin-right: 0%;
}

.row a {
    float:left;
    color:#010102;
    padding: 22px 16px;
    text-align: center;
    font-size: 18px;
    text-decoration: none;
    margin-right: 10px;
}

</style>