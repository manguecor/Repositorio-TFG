<template>
  <div id="app">
  <Footer/>
  <Header/>
  </div>
</template>
  
<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Footer from './components/MyFooter.vue'

import { mapActions } from 'vuex';

export default {
  name: 'HomeView',
  components: {
    Footer,
    Header
  },
  methods: {
    ...mapActions([
      'fetchAccessToken'
    ]),
  },
  created() {
    this.fetchAccessToken();
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
}
</style>
