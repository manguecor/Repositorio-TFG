<template>
  <div class="inicio">
  
      <img v-bind:src="require('../assets/logoNuevo.jpg')" alt="">
    
  </div>
</template>

<script>
export default {
  name: 'HelloWorld'
}
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
