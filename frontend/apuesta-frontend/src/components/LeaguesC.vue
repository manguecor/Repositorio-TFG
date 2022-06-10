<template>
<form @submit="create_league">
   <div class="row3">
            <div class="col-12">
                <br>
                <span class="titulos"> NUEVA LIGA</span><br><br>
                <img alt="SocialBet logo" src="../assets/logoNuevo.jpg" height="150" width="250">
                <br><br>
                <p v-if="errors.length">
                    <b>Por favor, corriga el(los) siguiente(s) error(es): </b>
                        <ul>
                            <li v-for="error in errors" v-bind:key="error.id">{{error}}</li>
                        </ul>
                </p>
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input id="name" v-model="league.name" placeholder="Nombre" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="name">Codigo:</label>
                    <input id="name" v-model="league.code" placeholder="Código" type="text" class="form" required>
                </div>
                <br>
                <div class="form-group">
                    <p>
                        <input type="submit" value="Crear liga" class="btn btn-success">
                    </p>
                    <a href="/leagues/myLeagues" class="btn btn-success">Volver</a>
                </div>
            </div>
        </div>
</form>
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
            },
            errors: [],
            leagues: []
        };
    },
    methods: {
        create_league: function(e){
            this.errors = [];
            
            let comp = false;
            for(let value in this.leagues) {
                console.log(this.leagues[value].code);
                console.log(this.league.code);
                comp = this.leagues[value].code == this.league.code;
                console.log(comp);
                if(comp) {
                    break;
                }
            }

            if(comp) {
                this.errors.push('El codigo de la liga ya existe');
            }
            
            e.preventDefault();
            
            if(this.errors.length==0) {
                LeagueService.postLeague(this.league).then(data => {
                    console.log(data);
                    window.location.href="/leagues/myLeagues";
                });
            }
            
        },

        getLeagues() {
            LeagueService.listAll().then(data => {
                this.leagues = data.data;
                console.log(this.leagues);
            })
        }
    },

    created() {
        this.getLeagues()
    }
}
</script>