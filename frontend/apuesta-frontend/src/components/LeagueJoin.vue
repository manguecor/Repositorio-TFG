<template>
   <div class="row3">
            <div class="col-12">
                <h2>Unirse a liga</h2>
                <br>
                <div class="form-group">
                    <label for="name">Codigo:</label>
                    <input id="name" v-model="code" placeholder="Code" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a href="/leagues/myLeagues" @click="join_league" class="btn btn-success">Unirse a liga</a>
                    <br>
                    <br>
                    <a href="/leagues/myLeagues" class="btn btn-info">Volver</a>
                </div>
            </div>
        </div>
</template>

<script>

import LeagueService from '../services/LeagueService'; 

export default {
    name: 'LeagueJoin',
    data() {
        return {
           code: null
        };
    },
    methods: {
        join_league(){
            LeagueService.joinLeague(this.code).then(data => {
                console.log(data);
                console.log(this.code);
            });
        }
    }  
}
</script>