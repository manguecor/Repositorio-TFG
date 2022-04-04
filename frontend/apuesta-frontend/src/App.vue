<template>
  <div id="app">
  <Competitions/>
  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
import Competitions from './components/Competitions.vue'

export default {
  name: 'App',
  components: {
    //HelloWorld,
    Competitions
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
