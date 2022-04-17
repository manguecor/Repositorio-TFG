import Vuex from 'vuex'


export default new Vuex.Store({
  
  state: {
    token: null
  },
  
  mutations: {
    setToken(state, payload){
      state.token = payload
    },

    updateAccessToken: (state, token) => {
      state.token = token;
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
    }
  },

  modules: {
  }
})