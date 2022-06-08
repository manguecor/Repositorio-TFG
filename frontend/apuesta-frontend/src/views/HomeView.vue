<template>
  <div class="home">
    <br>
    <br>
    <!--<img alt="SocialBet logo" src="../assets/logoNuevo.jpg">-->
    <div class="carrusel">
      <div class="card">
        <li><a href="/leagues/myLeagues">
        <img src="../assets/imagenInicio1.jpg" width="500" height="500">
        <div class="cuerpocard">
          <h5>Crea y únete a ligas con tus amigos</h5><br>
          <p>Puedes crear ligas para invitar a tus amigos o unirte a ligas ya creadas</p>
        </div>
        </a>
        </li>
      </div>
      <div class="card">
        <li><a href="/matches/today">
        <img src="../assets/imagenInicio2.jpg" width="300" height="300">
        <div class="cuerpocard">
          <h5>Crea distintas apuestas</h5><br>
          <p>Puedes crear apuestas de dos tipos, apuestas de equipo ganador y apuestas de resultado concreto</p>
        </div>
        </a>
        </li>
      </div>
      <div class="card">
        <li><a href="/competitions">
        <img src="../assets/imagenInicio3.jpg" width="300" height="300">
        <div class="cuerpocard">
          <h5>Consulta tus competiciones y equipos</h5><br>
          <p>Puedes consultar clasificaciones, goleadores y partidos de las grandes competiciones</p>
        </div>
        </a>
        </li>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'HomeView'
}
</script>

<style scoped>

.carrusel{
  display:flex;
  overflow-y: auto;
  padding: 16px;
  margin-left: 200px;
  margin-right: 200px;
  position: relative;
}

.card{
  margin-right: 10px;
  position: relative;
  width: 800px;
  height: 500px;
  list-style:none;
}

.cuerpocard{
  position: absolute;
  bottom: 0%;
  padding: 10px;
  background-color: black;
  color: white;
  height: 160px;
}

.cuerpocard h5,
.cuerpocard p{
  margin: 0;
  font-weight: bold;
}

.card img{
  width: 350px;
  object-fit: cover;
  height: 300px;
}
</style>
