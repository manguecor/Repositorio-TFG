<template>
  <section>
    <br>
    <h2>Prueba</h2>
    <div class="container">
      <br>
      <br>
      <a class="btn btn-success" v-on:click="saveBet">Guardar apuesta</a>
    </div>
  </section>
</template>

<script>



import BetService from "../services/BetService";

export default {
  name: "MyBet",
  methods: {
    
    saveBet() {
        let matches = [56,79,432]
        BetService.postBet(matches).then((response) => {
            console.log(response);
        })
    }
  }
}
</script>