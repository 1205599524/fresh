import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);
const state={ 
    name:''
  };
 const mutations = {
   correct(state){
       
   }
};
const store = new Vuex.Store({
    state,
   
    mutations
});
export default store;