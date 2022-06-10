<template>
   <div class="row3">
            <div class="col-12">
                <br><br>
                <span class="titulos">UNIRSE A LIGA</span><br><br>
                <img src="../assets/logoNuevo.jpg" width="250" height="150">
                <br><br>
                <div class="form-group">
                    <label for="name">Código:</label>
                    <input id="name" v-model="code" placeholder="Código" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a href="/leagues/myLeagues" @click="join_league" class="btn btn-success">Unirse a liga</a>
                    <br>
                    <br>
                    <a href="/leagues/myLeagues" class="btn btn-success">Volver</a>
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