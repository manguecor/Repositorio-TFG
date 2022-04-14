<template>
   <div class="row">
            <div class="col-12">
                <h2>Liga</h2>
                <br>
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input id="name" v-model="league.name" placeholder="Name" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <label for="name">Codigo:</label>
                    <input id="name" v-model="league.code" placeholder="Code" type="text" class="form">
                </div>
                <br>
                <div class="form-group">
                    <a href="/leagues" @click="create_league" class="btn btn-success">Crear liga</a>
                    <br>
                    <br>
                    <a href="/" class="btn btn-info">Volver</a>
                </div>
            </div>
        </div>
</template>

<script>

import LeagueService from '../services/LeagueService'; 

export default {
    name: 'MyLeague',
    data() {
        return {
            league: {
                name: null,
                code: null
            }
        };
    },
    methods: {
        create_league(){
            LeagueService.postLeague(this.league).then(data => {
                console.log(data);
            });
        }
    }  
}
</script>