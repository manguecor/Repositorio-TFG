import Vuex from 'vuex'


export default new Vuex.Store({
  
  state: {
    token: null,
    username: null
  },
  
  mutations: {
    setToken(state, payload){
      state.token = payload
    },

    updateAccessToken: (state, token) => {
      state.token = token;
    },

    setUsername(state, payload){
      state.username = payload
    }
  },
  
  actions: {
    
    getToken({commit}) {
      if(localStorage.getItem('token')) {
        commit('setToken', localStorage.getItem('token'))
      } else {
        commit('setToken', null)
      }
    },

    fetchAccessToken({ commit }) {
      commit('updateAccessToken', localStorage.getItem('token'));
    },

    getUsername({commit}) {
      if(localStorage.getItem('username')) {
        commit('setUsername', localStorage.getItem('username'))
      } else {
        commit('setUsername', null)
      }
    },
  },

  modules: {
  }
})